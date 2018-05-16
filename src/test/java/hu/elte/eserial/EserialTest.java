package hu.elte.eserial;

import hu.elte.eserial.annotation.Enumerated;
import hu.elte.eserial.annotation.ExcludeThis;
import hu.elte.eserial.annotation.enumeration.EnumeratedFormat;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EserialTest {

    public enum Role {
        Admin, User
    }

    public static class User {
        String name;
        int age;
        @ExcludeThis
        String password;
        List<Float> numbers;
        Date joined;
        @Enumerated(EnumeratedFormat.NAME)
        Role role;
        User friend;

        public User() {}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<Float> getNumbers() {
            return numbers;
        }

        public void setNumbers(List<Float> numbers) {
            this.numbers = numbers;
        }

        public Date getJoined() {
            return joined;
        }

        public void setJoined(Date joined) {
            this.joined = joined;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public User getFriend() {
            return friend;
        }

        public void setFriend(User friend) {
            this.friend = friend;
        }
    }

    @Test
    public void toJson_fromJson_SerializesAndParsesTheObjectCorrectly() {

        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(now.getTime());
        tomorrow.add(Calendar.DATE, 1);

        User user1 = new User();
        user1.setName("user1Name");
        user1.setAge(10);
        user1.setPassword("user1Password");
        user1.setNumbers(Arrays.asList(10.0f, 10.5f, 11.0f, 11.5f));
        user1.setRole(Role.Admin);
        user1.setJoined(now.getTime());

        User user2 = new User();
        user2.setName("user2Name");
        user2.setAge(20);
        user2.setPassword("user2Password");
        user2.setNumbers(Arrays.asList(20.0f, 20.5f, 21.0f, 21.5f));
        user2.setRole(Role.User);
        user2.setJoined(tomorrow.getTime());

        user1.setFriend(user2);
        user2.setFriend(user1);

        String json = Eserial.toJson(user1);
        System.out.println(json);

        User user3 = Eserial.fromJson(User.class, json);

        assertEquals("user1Name", user3.getName());
        assertEquals(10, user3.getAge());
        assertNull(user3.getPassword());
        assertEquals(Arrays.asList(10.0f, 10.5f, 11.0f, 11.5f), user3.getNumbers());
        assertEquals(Role.Admin, user3.getRole());
        assertEquals(now.getTime(), user3.getJoined());

        assertEquals("user2Name", user3.getFriend().getName());
        assertEquals(20, user3.getFriend().getAge());
        assertNull(user3.getFriend().getPassword());
        assertEquals(Arrays.asList(20.0f, 20.5f, 21.0f, 21.5f), user3.getFriend().getNumbers());
        assertNull(user3.getFriend().getFriend());
        assertEquals(Role.User, user3.getFriend().getRole());
        assertEquals(tomorrow.getTime(), user3.getFriend().getJoined());
    }
}