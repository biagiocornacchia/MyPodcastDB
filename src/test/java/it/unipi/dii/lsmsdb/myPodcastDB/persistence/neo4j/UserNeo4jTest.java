package it.unipi.dii.lsmsdb.myPodcastDB.persistence.neo4j;

import it.unipi.dii.lsmsdb.myPodcastDB.model.Author;
import it.unipi.dii.lsmsdb.myPodcastDB.model.Podcast;
import it.unipi.dii.lsmsdb.myPodcastDB.model.User;

import java.util.List;

public class UserNeo4jTest {

    PodcastNeo4j podcastNeo4j;
    UserNeo4j userNeo4j;
    AuthorNeo4j authorNeo4j;

    public static void  main(String[] args){

        Neo4jManager manager = Neo4jManager.getInstance();
        manager.openConnection();
        UserNeo4jTest test = new UserNeo4jTest();

        test.addUserTest();
        test.addUserLikesPodcastTest();
        test.addUserWatchLaterPodcastTest();
        test.addUserFollowUserTest();
        test.addUserFollowAuthorTest();
        test.updateUserTest();
        test.deleteUserTest();
        test.deleteUserLikesPodcastTest();
        test.deleteAllUserLikesPodcastTest();
        test.deleteUserWatchLaterPodcastTest();
        test.deleteAllUserWatchLaterPodcastTest();
        test.deleteUserFollowUserTest();
        test.deleteAllUserFollowUserTest();
        test.deleteUserFollowAuthorTest();
        test.deleteAllUserFollowAuthorTest();
        test.showFollowedUserTest();

        manager.closeConnection();
    }

    public UserNeo4jTest(){
        this.userNeo4j = new UserNeo4j();
        this.authorNeo4j = new AuthorNeo4j();
        this.podcastNeo4j = new PodcastNeo4j();
    }

    /*
    CYPHER TO TEST
    CREATE (p:Podcast{name: "podcast test", podcastId: "1234567890", class: "test"});
    CREATE (p:Podcast{name: "podcast test1", podcastId: "1234567891", class: "test"});
    CREATE (p:Podcast{name: "podcast test2", podcastId: "1234567892", class: "test"});
    CREATE (p:Podcast{name: "podcast test3", podcastId: "1234567893", class: "test"});
    CREATE (p:Podcast{name: "podcast test4", podcastId: "1234567894", class: "test"});
    CREATE (a:Author{name: "author test", class: "test"});
    CREATE (a:Author{name: "author test1", class: "test"});
    CREATE (a:Author{name: "author test2", class: "test"});
    CREATE (a:Author{name: "author test3", class: "test"});
    MATCH (p:Podcast{class:"test"}), (a:Author{class:"test"}) RETURN p,a;

    TO CLEAN
    MATCH (p:Podcast{class:"test"}), (a:Author{class:"test"}) DETACH DELETE p,a;

    */

    public void addUserTest(){

        User user = new User();
        user.setUsername("user test");
        boolean result = this.userNeo4j.addUser(user);
        if(result && this.userNeo4j.checkUserExists(user))
            System.out.println("[+] addUser");
        else
            System.err.println("[-] addUser");

    }

    public void addUserLikesPodcastTest(){

        User user = new User();
        Podcast podcast = new Podcast();
        user.setUsername("user test");
        podcast.setId("1234567890");

        boolean result = this.userNeo4j.addUserLikesPodcast(user, podcast);
        if(result && this.userNeo4j.checkUserLikesPodcastExists(user, podcast))
            System.out.println("[+] addUserLikesPodcast");
        else
            System.err.println("[-] addUserLikesPodcast");

    }

    public void addUserWatchLaterPodcastTest(){

        User user = new User();
        Podcast podcast = new Podcast();
        user.setUsername("user test");
        podcast.setId("1234567891");

        boolean result = this.userNeo4j.addUserWatchLaterPodcast(user, podcast);
        if(result && this.userNeo4j.checkUserWatchLaterPodcastExists(user, podcast))
            System.out.println("[+] addUserWatchLaterPodcast");
        else
            System.err.println("[-] addUserWatchLaterPodcast");

    }

    public void addUserFollowUserTest(){

        User user1 = new User();
        User user2 = new User();
        user1.setUsername("user test");
        user2.setUsername("user test2");

        this.userNeo4j.addUser(user2);
        boolean result = this.userNeo4j.addUserFollowUser(user1,user2);
        if(result && this.userNeo4j.checkUserFollowUserExists(user1, user2))
            System.out.println("[+] addUserFollowUser");
        else
            System.err.println("[-] addUserFollowUser");

    }

    public void addUserFollowAuthorTest(){

        User user = new User();
        Author author = new Author();
        user.setUsername("user test");
        author.setName("author test");
        boolean result = this.userNeo4j.addUserFollowAuthor(user, author);
        if(result && this.userNeo4j.checkUserFollowAuthorExists(user, author))
            System.out.println("[+] addUserFollowAuthor");
        else
            System.err.println("[-] addUserFollowAuthor");

    }

    public void updateUserTest(){

        User user = new User();
        user.setUsername("user test");
        String newUsername = "user test3";
        boolean result = this.userNeo4j.updateUser(user, newUsername);
        if(result && user.getUsername().equals(newUsername))
            System.out.println("[+] updateUser");
        else
            System.err.println("[-] updateUser");

    }

    public void deleteUserTest(){

        User user = new User();
        user.setUsername("user test4");
        this.userNeo4j.addUser(user);

        boolean result = this.userNeo4j.deleteUser(user);
        if(result && !this.userNeo4j.checkUserExists(user))
            System.out.println("[+] deleteUser");
        else
            System.err.println("[-] deleteUser");

    }

    public void deleteUserLikesPodcastTest(){

        User user = new User();
        Podcast podcast = new Podcast();
        user.setUsername("user test3");
        podcast.setId("1234567890");

        boolean result = this.userNeo4j.deleteUserLikesPodcast(user, podcast);
        if(result && !this.userNeo4j.checkUserLikesPodcastExists(user, podcast))
            System.out.println("[+] deleteUserLikesPodcast");
        else
            System.err.println("[-] deleteUserLikesPodcast");

    }

    public void deleteAllUserLikesPodcastTest(){

        User user = new User();
        Podcast podcast1 = new Podcast();
        Podcast podcast2 = new Podcast();
        Podcast podcast3 = new Podcast();
        user.setUsername("user test3");
        podcast1.setId("1234567892");
        podcast2.setId("1234567893");
        podcast3.setId("1234567894");
        this.userNeo4j.addUserLikesPodcast(user, podcast1);
        this.userNeo4j.addUserLikesPodcast(user, podcast2);
        this.userNeo4j.addUserLikesPodcast(user, podcast3);

        boolean result = this.userNeo4j.deleteAllUserLikesPodcast(user);
        if(result && !this.userNeo4j.checkAllUserLikesPodcastExists(user))
            System.out.println("[+] deleteAllUserLikesPodcast");
        else
            System.err.println("[-] deleteAllUserLikesPodcast");

    }

    public void deleteUserWatchLaterPodcastTest(){

        User user = new User();
        Podcast podcast = new Podcast();
        user.setUsername("user test3");
        podcast.setId("1234567891");

        boolean result = this.userNeo4j.deleteUserWatchLaterPodcast(user, podcast);
        if(result && !this.userNeo4j.checkUserWatchLaterPodcastExists(user, podcast))
            System.out.println("[+] deleteUserWatchLaterPodcast");
        else
            System.err.println("[-] deleteUserWatchLaterPodcast");

    }

    public void deleteAllUserWatchLaterPodcastTest(){

        User user = new User();
        Podcast podcast1 = new Podcast();
        Podcast podcast2 = new Podcast();
        Podcast podcast3 = new Podcast();
        user.setUsername("user test3");
        podcast1.setId("1234567892");
        podcast2.setId("1234567893");
        podcast3.setId("1234567894");
        this.userNeo4j.addUserWatchLaterPodcast(user, podcast1);
        this.userNeo4j.addUserWatchLaterPodcast(user, podcast2);
        this.userNeo4j.addUserWatchLaterPodcast(user, podcast3);

        boolean result = this.userNeo4j.deleteAllUserWatchLaterPodcast(user);
        if(result && !this.userNeo4j.checkAllUserWatchLaterPodcastExists(user))
            System.out.println("[+] deleteAllUserWatchLaterPodcast");
        else
            System.err.println("[-] deleteAllUserWatchLaterPodcast");
    }


    private void showFollowedUserTest() {
        User user = new User();
        user.setUsername("silverelephant716273");
        List<String> podcasts = this.userNeo4j.showFollowedUsers(user, 10);
        if(podcasts != null)
            System.out.println("[+] showFollowedUserTest");
        else
            System.err.println("[-] showFollowedUserTest");

        for(String podcast : podcasts)
            System.out.println(podcast);
    }

    public void deleteUserFollowUserTest() {
        User user = new User();
        user.setUsername("test1");
        this.userNeo4j.addUser(user);
        user.setUsername("test2");
        this.userNeo4j.addUser(user);
        this.userNeo4j.addUserFollowUser("test1", "test2");
        this.userNeo4j.deleteUserFollowUser("test1", "test2");

        boolean test = this.userNeo4j.findUserFollowsUser("test1", "test2");
        if (!test)
            System.out.println("[+] deleteUserFollowUser");
        else
            System.err.println("[-] deleteUserFollowUser");

        this.userNeo4j.deleteUser("test1");
        this.userNeo4j.deleteUser("test2");
    }

    public void deleteAllUserFollowUserTest() {
        User user = new User();
        user.setUsername("test1");
        this.userNeo4j.addUser(user);
        user.setUsername("test2");
        this.userNeo4j.addUser(user);
        user.setUsername("test3");
        this.userNeo4j.addUser(user);
        this.userNeo4j.addUserFollowUser("test1", "test2");
        this.userNeo4j.addUserFollowUser("test1", "test3");
        this.userNeo4j.deleteAllUserFollowUser("test1");

        boolean test1 = this.userNeo4j.findUserFollowsUser("test1", "test2");
        boolean test2 = this.userNeo4j.findUserFollowsUser("test1", "test3");
        if (!test1 && !test2)
            System.out.println("[+] deleteUserFollowUserTest");
        else
            System.err.println("[-] deleteUserFollowUserTest");

        this.userNeo4j.deleteUser("test1");
        this.userNeo4j.deleteUser("test2");
        this.userNeo4j.deleteUser("test3");
    }

    public void deleteUserFollowAuthorTest() {
        User user = new User();
        user.setUsername("test1");
        this.userNeo4j.addUser(user);
        Author author = new Author();
        author.setName("test2");
        this.authorNeo4j.addAuthor(author);

        this.userNeo4j.addUserFollowAuthor("test1", "test2");
        this.userNeo4j.deleteUserFollowAuthor("test1", "test2");

        boolean test = this.userNeo4j.findUserFollowsAuthor("test1", "test2");
        if (!test)
            System.out.println("[+] deleteUserFollowAuthor");
        else
            System.err.println("[-] deleteUserFollowAuthor");

        this.userNeo4j.deleteUser("test1");
        this.authorNeo4j.deleteAuthor("test2");
    }

    public void deleteAllUserFollowAuthorTest() {
        User user = new User();
        user.setUsername("test1");
        this.userNeo4j.addUser(user);
        Author author = new Author();
        author.setName("test2");
        this.authorNeo4j.addAuthor(author);
        author.setName("test3");
        this.authorNeo4j.addAuthor(author);

        this.userNeo4j.addUserFollowAuthor("test1", "test2");
        this.userNeo4j.addUserFollowAuthor("test1", "test3");
        this.userNeo4j.deleteAllUserFollowAuthor("test1");

        boolean test1 = this.userNeo4j.findUserFollowsAuthor("test1", "test2");
        boolean test2 = this.userNeo4j.findUserFollowsAuthor("test1", "test3");
        if (!test1 && !test2)
            System.out.println("[+] deleteAllUserFollowAuthor");
        else
            System.err.println("[-] deleteAllUserFollowAuthor");

        this.userNeo4j.deleteUser("test1");
        this.authorNeo4j.deleteAuthor("test2");
        this.authorNeo4j.deleteAuthor("test3");
    }
}
