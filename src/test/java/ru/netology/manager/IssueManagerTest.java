package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.util.IssueComparatorAsc;
import ru.netology.util.IssueComparatorDesc;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    int nonexistentID = 4;
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    IssueComparatorAsc straight = new IssueComparatorAsc();
    IssueComparatorDesc reversed = new IssueComparatorDesc();
    Issue first = new Issue(1, 20, false, "jsedding", new HashSet<String>(Arrays.asList("Task")), new HashSet<String>(Arrays.asList("Master")));
    Issue second = new Issue(2, 5, false, "paschi", new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("Junior")));
    Issue third = new Issue(3, 10, true, "paschi", new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("Master")));

    @BeforeEach
    void setUp() {
        manager.addAll(List.of(first, second, third));
    }

    /* Добавление Issue */

    @Test
    public void shouldAddOne() {
        Issue fourth = new Issue(4, 7, true, "jsedding", new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("Master")));
        manager.add(fourth);
        Collection<Issue> expected = List.of(first, second, third, fourth);
        Collection<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    /* Отображение списка открытых и закрытых Issue (два отдельных метода) */

    @Test
    public void shouldShowOpenIssues() {
        Collection<Issue> expected = List.of(first, second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowClosedIssues() {
        Collection<Issue> expected = List.of(third);
        Collection<Issue> actual = manager.showClosedIssues();
        assertEquals(expected, actual);
    }

    /* Фильтрация (3 отдельных метода):
    По имени автора (кто создал)
    По Label'у
    По Assignee (на кого назначено) */

    @Test
    public void shouldFilterByAuthor() {
        String author = "paschi";
        Predicate<String> equalAuthor = t -> t.equals(author);
        Collection<Issue> expected = List.of(second, third);
        Collection<Issue> actual = manager.filterByAuthor(equalAuthor);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByLabel() {
        Set<String> label = new HashSet<>(Arrays.asList("Bug"));
        Predicate<Set> equalLabel = t -> t.equals(label);
        Collection<Issue> expected = List.of(second, third);
        Collection<Issue> actual = manager.filterByLabel(equalLabel);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssignee() {
        Set<String> assignee = new HashSet<>(Arrays.asList("Master"));
        Predicate<Set> equalAssignee = t -> t.equals(assignee);
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.filterByAssignee(equalAssignee);
        assertEquals(expected, actual);
    }

    /* Сортировка */

    @Test
    public void shouldSortByCommentsQuantityAsc() {
        Collection<Issue> expected = List.of(first, third, second);
        Collection<Issue> actual = manager.sortByCommentsQuantity(straight);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByCommentsQuantityDesc() {
        Collection<Issue> expected = List.of(second, third, first);
        Collection<Issue> actual = manager.sortByCommentsQuantityReversed(reversed);
        assertEquals(expected, actual);
    }

    /* Закрытие/открытие Issue по ID */

    @Test
    public void shouldOpenIssue() {
        manager.openIssue(3);
        Collection<Issue> expected = List.of(first, second, third);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCloseIssue() {
        manager.closeIssue(1);
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.showClosedIssues();
        assertEquals(expected, actual);
    }

    /* Тесты для покрытия */

    @Test
    public void shouldNotOpenIssue() {
        manager.openIssue(nonexistentID);
        Collection<Issue> expected = List.of(first, second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotCloseIssue() {
        manager.closeIssue(nonexistentID);
        Collection<Issue> expected = List.of(first, second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }
}