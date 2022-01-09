package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.util.NotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    int nonexistentID = 4;
    IssueRepository repository = new IssueRepository();
    //    Issue first = new Issue(1, 20, false, "jsedding", "Task", "Master");
    Issue first = new Issue(1, 20, false, "jsedding", new HashSet<String>(Arrays.asList("Task")), new HashSet<String>(Arrays.asList("Master")));
    //    Issue second = new Issue(2, 5, false, "paschi", "Bug", "Junior");
    Issue second = new Issue(2, 5, false, "paschi", new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("Junior")));
    //    Issue third = new Issue(3, 10, true, "paschi", "Bug", "Master");
    Issue third = new Issue(3, 10, true, "paschi", new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("Master")));

    @BeforeEach
    void setUp() {
        repository.saveAll(List.of(first, second, third));
    }

    @Test
    public void shouldFindByID() {
        Issue expected = third;
        Issue actual = repository.findByID(3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByID() {
        assertNull(repository.findByID(nonexistentID));
    }

    @Test
    public void shouldNotCloseIssue() {
        assertThrows(NotFoundException.class, () -> repository.closeIssue(nonexistentID));
    }

    @Test
    public void shouldNotOpenIssue() {
        assertThrows(NotFoundException.class, () -> repository.openIssue(nonexistentID));
    }
}