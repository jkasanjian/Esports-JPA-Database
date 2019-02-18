/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

import csulb.cecs323.model.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 * Application for creating a database to model an e-sports League of Legends tournament
 * Created for Database Fundamentals homework assignment
 */
public class Homework4Application {
   private EntityManager entityManager;

   private static final Logger LOGGER = Logger.getLogger(Homework4Application.class.getName());

   public Homework4Application(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("homework4_PU");
      EntityManager manager = factory.createEntityManager();
      Homework4Application hw4application = new Homework4Application(manager);

      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();
      boolean quit = true;
      int choice;

      do{
         System.out.println("\nMain Menu: \n" +
                 "1. Queries\n" +
                 "2. Create TeamMember Entity\n" +
                 "3. Remove Team Entity\n" +
                 "4. Exit");

         System.out.print("Choice: ");
         choice = Integer.parseInt(getString());
         switch(choice){
            case 1:
               System.out.println("\n1. Displays the names of the two teams in the longest match of the summer 2015 split and how many team members they have\n" +
                                "2. Displays the name and cs of the player on the team TSM that has the most creep score (cs)\n" +
                                "3. Displays the number of kills and deaths in all games played by Matt Szeto in the Spring 2015 split\n");
               int choice2 = Integer.parseInt(getString());
               switch(choice2){
                  case 1:
                     Query query1 = manager.createNativeQuery("SELECT t.name, MAX(m.LENGTHOFMATCH), COUNT(tm.GAMERTAG)\n" +
                             "FROM teams t INNER JOIN matchinfos mi on t.TEAMID = mi.TEAM_TEAMID\n" +
                             "              INNER JOIN matches m on mi.MATCH_ID = m.ID\n" +
                             "              INNER JOIN splits s on m.SPLIT_CODE = s.CODE\n" +
                             "              INNER JOIN teammembers tm on t.TEAMID = tm.TEAM_TEAMID\n" +
                             "WHERE s.year = '2015' AND s.season = 'summer'\n" +
                             "GROUP BY t.name;");
                     try {
                        Object[] team = (Object[]) query1.getSingleResult();
                        System.out.println("Team name: " + team[0] +
                                " Length of Match: " + team[1] +
                                " Number of Team Members" + team[2]);
                     }catch (NoResultException e){
                        System.out.println("No Result");
                        break;
                     }

                     break;
                  case 2:
                     Query query2 = manager.createNativeQuery("SELECT tm.fname, tm.lname, tm.cs\n" +
                             "FROM teammembers tm INNER JOIN teams t on tm.TEAM_TEAMID = t.TEAMID\n" +
                             "                    INNER JOIN playermatchstats pms on tm.ID = pms.PLAYER_ID\n" +
                             "WHERE NOT EXISTS(SELECT* FROM teammembers tm1\n" +
                             "                                INNER JOIN teams t1 on tm1.TEAM_TEAMID = t1.TEAMID\n" +
                             "                                INNER JOIN playermatchstats pms1 on tm1.ID = pms1.PLAYER_ID\n" +
                             "                 WHERE pms1.cs > pms.cs);");
                     try {
                        Object[] player = (Object[]) query2.getSingleResult();
                        System.out.println("Player Name: " + player[0] + " " + player[1] +
                                "Creep Score: " + player[2]);
                     }catch (NoResultException e){
                        System.out.println("No Result");
                        break;
                     }
                     break;
                  case 3:
                     Query query3 = manager.createNativeQuery("SELECT tm.fName, tm.lName, pms.kills, pms.deaths\n" +
                             "FROM teammembers tm INNER JOIN playermatchstats pms on tm.ID = pms.PLAYER_ID\n" +
                             "                    INNER JOIN matches m on pms.MATCH_ID = m.ID\n" +
                             "                    INNER JOIN splits s on m.split_code\n" +
                             "WHERE EXISTS (SELECT *\n" +
                             "              FROM playermatchstats pms1 INNER JOIN teammembers tm1 on pms1.PLAYER_ID = tm1.ID\n" +
                             "              WHERE tm1.fName = 'Matt' AND tm1.lName = 'Szeto');");
                     try {
                        Object[] playerStats = (Object[]) query3.getSingleResult();
                        System.out.println("Player Name: " + playerStats[0] + " " + playerStats[1] +
                                " Kills: " + playerStats[2] +
                                " Deaths: " + playerStats[3]);
                     }catch (NoResultException e){
                        System.out.println("No Result");
                        break;
                     }
                     break;
                  case 4:
               }
               break;

            case 2:
               //create team member entity
               System.out.print("First Name: ");
               String fName = getString();
               System.out.print("Last Name: ");
               String lName = getString();
               System.out.print("Gamertag: ");
               String gamertag = getString();
               System.out.print("Age: ");
               int age = Integer.parseInt(getString());
               System.out.print("Gender (M/F/N): ");
               String gender = getString();
               System.out.print("Position (top/mid/jungle/support/adc): ");
               String position = getString();
               System.out.print("Main Champion: ");
               String mainChamp = getString();

               hw4application.createTeamMemberEntity(fName, lName, gamertag, age, gender, position, mainChamp);

               break;

            case 3:
               //remove team entity
               System.out.print("Enter the name of the team that you would like to remove: ");
               String removeInput = getString();
               Query deleteQuery = manager.createNativeQuery(
                       "DELETE FROM teams t WHERE t.name = :name");
               int deleteCount = deleteQuery.setParameter("name", removeInput).executeUpdate();
               break;
            case 4:
               //exit
               quit = false;
               break;
         }

      }while (quit);



      tx.commit();
      LOGGER.fine("End of Transaction");

   }



   /**
    * Create and persist a TeamMember object to the database.
    */
   public void createTeamMemberEntity(String fName, String lname, String gamertag, int age,
                                      String gender, String position, String mainChampion) {
      LOGGER.fine("Creating TeamMember object");

      TeamMember player = new TeamMember();
      player.setfName(fName);
      player.setlName(lname);
      player.setGamertag(gamertag);
      player.setAge(age);
      Gender genderEnum = Gender.valueOf(gender);
      player.setGender(genderEnum);
      Position positionEnum = Position.valueOf(position);
      player.setPosition(positionEnum);
      player.setMainChampion(mainChampion);

      LOGGER.fine("Persisting TeamMember object to DB");
      this.entityManager.persist(player);

   }


   /**
    * Uses scanner to get input as a string
    * @return string with user input
    */
   public static String getString(){
      Scanner in = new Scanner(System.in); //scanner variable to take input
      return in.nextLine().trim();
   }





}
