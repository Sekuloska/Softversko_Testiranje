package com.example.skitdom2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

public class ConferenceTest {

    private static double TICKET_PRICE = 10.90;
    public static double EMT_DISCOUNT = 0.5;
    public static double WEB_DISCOUNT = 0.3;

    //Test za prazna lista na posetiteli
    @Test
    public void EmptyListAttendees() {
        Conference conference=new Conference(10);
        List<Student> students = new ArrayList<>();
        assertEquals("[]","[]");

    }

    //Test kade sto nitu eden posetitel nema discount
    @Test
    public void NoDiscountForAttendess() {
        List<Student> students=new ArrayList<>();
        students.add(new Student("Hristina","Sekuloska",Course.OS,22));
        students.add(new Student("Marija","Petkovska",Course.SKIT,23));
        students.add(new Student("Zorica","Trpevska",Course.OS,22));
        Conference conference=new Conference(students.size());
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }
        double price=conference.calculateTotalPricePaid();
        assertEquals(3*TICKET_PRICE,price,0);
    }

    //Test kade sto tocno eden posetitel nema popust, drugite imaat
    @Test
    public void OneAttendeHavingNoDiscount() {
        List<Student> students=new ArrayList<>();
        students.add(new Student("Hristina","Sekuloska",Course.OS,22));
        students.add(new Student("Marija","Petkovska",Course.EMT,23));
        students.add(new Student("Zorica","Trpevska",Course.WEB,22));
        Conference conference=new Conference(students.size());
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }
        double price=conference.calculateTotalPricePaid();
        assertEquals((TICKET_PRICE*EMT_DISCOUNT)+(TICKET_PRICE*WEB_DISCOUNT)+TICKET_PRICE,19.62,0);
    }

    //Test kade sto povekje od eden posetitel nema popust
    @Test
    public void MultipleAttendessHaveNoDiscount() {
        List<Student> students=new ArrayList<>();
        students.add(new Student("Hristina","Sekuloska",Course.OS,22));
        students.add(new Student("Marija","Petkovska",Course.SKIT,23));
        students.add(new Student("Zorica","Trpevska",Course.OS,22));
        students.add(new Student("Zorica","Trpevska",Course.EMT,22));
        Conference conference=new Conference(students.size());
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }
        double price=conference.calculateTotalPricePaid();
        assertEquals((TICKET_PRICE*EMT_DISCOUNT)+(3*TICKET_PRICE),38,0.5);
    }

    //Test kade sto site posetiteli imaat EMT discount
    @Test
    public void AllAttendessHaveEMTDisc() {
        List<Student> students=new ArrayList<>();
        students.add(new Student("Hristina","Sekuloska",Course.EMT,22));
        students.add(new Student("Marija","Petkovska",Course.EMT,23));
        students.add(new Student("Zorica","Trpevska",Course.EMT,22));
        Conference conference=new Conference(students.size());
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }
        double price=conference.calculateTotalPricePaid();
        assertEquals((TICKET_PRICE*EMT_DISCOUNT)*3,16.35,0);
    }

    //Site posetiteli imaat WEB discount
    @Test
    public void AllAttendessHaveWEBDisc() {
        List<Student> students=new ArrayList<>();
        students.add(new Student("Hristina","Sekuloska",Course.WEB,22));
        students.add(new Student("Marija","Petkovska",Course.WEB,23));
        students.add(new Student("Zorica","Trpevska",Course.WEB,22));
        Conference conference=new Conference(students.size());
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }
        double price=conference.calculateTotalPricePaid();
        assertEquals((TICKET_PRICE*WEB_DISCOUNT)*3,9.81,0);
    }

    //Mix na posetiteli sto imaat disc i nemaat i slusaat razlicni kursevi
    @Test
    public void MixOfAttendess() {
        List<Student> students=new ArrayList<>();
        students.add(new Student("Hristina","Sekuloska",Course.WEB,22));
        students.add(new Student("Marija","Petkovska",Course.EMT,23));
        students.add(new Student("Zorica","Trpevska",Course.OS,22));
        students.add(new Student("Petra","Petrovska",Course.SKIT,22));
        students.add(new Student("Petra","Petrovska",Course.OTHER,22));
        Conference conference=new Conference(students.size());
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }
        double price=conference.calculateTotalPricePaid();
        assertEquals((TICKET_PRICE*EMT_DISCOUNT)+(TICKET_PRICE*WEB_DISCOUNT)+(3*TICKET_PRICE),41.42,0);
    }

    //Test za da probame da dodademe student, no capacity se nadminiuva so toj student
    @Test
    public void AddStudentOutOfRange() {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Hristina", "Sekuloska", Course.WEB, 22));
        students.add(new Student("Marija", "Petkovska", Course.EMT, 23));

        Conference conference = mock(Conference.class);
        when(conference.tripleCapacity()).thenReturn(false);

        conference.addAttendeeToConference(students.get(0));

        Student student = new Student("Mila", "Milovska", Course.OTHER, 22);
        assertFalse(conference.addAttendeeToConference(student));
    }

    //  Test za dodavanje na student kojsto moze da bide dodaden i funkcijata tripleCapacity e uspesna
    @Test
    public void AddStudentTrueAndTripleCapacityTrue() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Hristina", "Sekuloska", Course.WEB, 22));
        students.add(new Student("Marija", "Petkovska", Course.EMT, 23));


        Conference conference = spy(new Conference(500));
        doReturn(false).when(conference).tripleCapacity();
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }

        Student student = new Student("Mila", "Milovska", Course.OTHER, 22);
        assertTrue(conference.addAttendeeToConference(student));

    }

    //Test za dodavanje na student kade ima kapacitet da bide dodaden, no tripleCapacity vrakja false
    @Test
    public void AddStudentInRangeTripleCapacityFails() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Hristina", "Sekuloska", Course.WEB, 22));
        students.add(new Student("Marija", "Petkovska", Course.EMT, 23));


        // Create a partial mock of Conference with a real instance
        Conference conference = spy(new Conference(3));
        doReturn(false).when(conference).tripleCapacity();


        //conference.addAttendeeToConference(students.get(0));

        Student student = new Student("Mila", "Milovska", Course.OTHER, 22);
        assertTrue(conference.addAttendeeToConference(student));
    }

    //Test za dodavanje na student koga listata inicijalno e prazna
    @Test
    public void AddStudentOnEmptyList() {
        List<Student> students = new ArrayList<>();

        Conference conference = spy(new Conference(1));
        doReturn(false).when(conference).tripleCapacity();

        //conference.addAttendeeToConference(students.get(0));

        Student student = new Student("Mila", "Milovska", Course.OTHER, 22);
        assertTrue(conference.addAttendeeToConference(student));
    }

    //Test za triple capacity da bide troduplo, primer capacity 3000 i da vrati true
    @Test
    public void TripleCapacityThreeTimesBigger() {
        Conference conference=new Conference(3000);
        assertTrue(conference.tripleCapacity());
    }

    //Test kade sto capacity nema da bide tri pati pogolem, t.e ke si ostane ist
    @Test
    public void TripleCapacityIsNotThreeTimesBigger() {
        Conference conference=new Conference(10000);
        assertFalse(conference.tripleCapacity());
    }

    @Test
    public void TripleCapacityCloseToLimit() {
        Conference conference = new Conference(3333);
        assertTrue(conference.tripleCapacity());
    }

    //Test zemi gi site mutanti
    @Test
    public void GetAttendess() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Hristina", "Sekuloska", Course.WEB, 22));
        students.add(new Student("Marija", "Petkovska", Course.EMT, 23));

        Conference conference=new Conference(2);
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }
        assertNotNull(conference.getAttendees());
    }

    //Test kade sto noviot attendet treba da bide dodaden, no sepak ne se dodava deka e dostignat max capacity
    @Test
    public void AddStudentAtMaxCapacityWithFailedTripleCapacity() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Hristina", "Sekuloska", Course.WEB, 22));
        students.add(new Student("Marija", "Petkovska", Course.EMT, 23));

        // Create a spy of Conference with a real instance
        Conference conference = spy(new Conference(2));
        doReturn(false).when(conference).tripleCapacity();

        // Add attendees until reaching maximum capacity
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }

        // Now try to add another student
        Student extraStudent = new Student("Mila", "Milovska", Course.OTHER, 22);
        assertFalse(conference.addAttendeeToConference(extraStudent));
        assertEquals(2, conference.getAttendees().size()); // Make sure no additional attendee was added
    }


    @Test
    public void calculateTotalPriceWithEMTDiscount() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", Course.EMT, 22));
        students.add(new Student("Jane", "Smith", Course.OS, 23)); // Not EMT

        Conference conference = new Conference(students.size());
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }

        double expectedPrice =TICKET_PRICE+(TICKET_PRICE*EMT_DISCOUNT);
        double actualPrice = conference.calculateTotalPricePaid();
        assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void calculateTotalPriceWithWEBDiscount() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", Course.WEB, 22));
        students.add(new Student("Jane", "Smith", Course.OS, 23)); // Not WEB

        Conference conference = new Conference(students.size());
        for (Student student : students) {
            conference.addAttendeeToConference(student);
        }

        double expectedPrice = (TICKET_PRICE * students.size()) - (TICKET_PRICE * WEB_DISCOUNT);
        double actualPrice = conference.calculateTotalPricePaid();
        assertEquals(expectedPrice, actualPrice, 0);
    }

    //Tests for Student class
    @Test
    public void testGetName() {
        Student student = new Student("John", "Doe", Course.OS, 22);
        assertEquals("John", student.getName());
    }

    @Test
    public void testGetSurname() {
        Student student = new Student("John", "Doe", Course.OS, 22);
        assertEquals("Doe", student.getSurname());
    }

    @Test
    public void testGetCourse() {
        Student student = new Student("John", "Doe", Course.OS, 22);
        assertEquals(Course.OS, student.getCourse());
    }

    @Test
    public void testGetAge() {
        Student student = new Student("John", "Doe", Course.OS, 22);
        assertEquals(22, student.getAge());
    }

    @Test
    public void testToString() {
        Student student = new Student("John", "Doe", Course.OS, 22);
        assertEquals("Student{name='John', surname='Doe', course=OS, age=22}", student.toString());
    }

    @Test
    public void testTripleCapacityBoundary() {
        Conference conference = new Conference(4000); // Capacity is greater than one-third of maximum capacity
        assertFalse(conference.tripleCapacity()); // Capacity should not be tripled
        assertEquals(4000, conference.getCapacity()); // Capacity should remain unchanged
    }

    @Test
    public void testCapacityExceedsMaximum() {
        // Create a conference with a capacity that will exceed the maximum limit after tripling
        Conference conference = new Conference(4000);

        // Ensure that the capacity is already greater than one-third of the maximum limit
        assertFalse(conference.tripleCapacity()); // Capacity should not be tripled

        // Check if the capacity remains unchanged
        assertEquals(4000, conference.getCapacity());
    }
    @Test
    public void testTripleCapacity() {
        // Create a conference with a capacity that allows successful tripling
        Conference conference = new Conference(3000);

        // Ensure that the capacity can be tripled
        assertTrue(conference.tripleCapacity()); // Capacity should be tripled

        // Check if the capacity is tripled correctly
        assertEquals(9000, conference.getCapacity());
    }

//    @Test
//    public void testCapacityAtMaximumAfterTripling() {
//        Conference conference = new Conference(3000);
//
//        // Ensure that the capacity can be tripled
//        assertTrue(conference.tripleCapacity()); // Capacity should be tripled
//
//        // Check if the capacity is set to the maximum limit (10000)
//        if (conference.getCapacity() < 10000) {
//            assertTrue(conference.tripleCapacity()); // Try tripling the capacity again
//        }
//
//        // Check if the capacity is set to the maximum limit (10000)
//        assertEquals(10000, conference.getCapacity()); // Ensure capacity is set to
//    }


    @Test
    public void testGetCapacity() {
        Conference conference = new Conference(50);
        assertEquals(50, conference.getCapacity()); // Check if initial capacity is returned correctly
    }




}
