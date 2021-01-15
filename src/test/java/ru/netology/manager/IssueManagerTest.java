package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepo;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueRepo repository = new IssueRepo();
    private IssueManager manager = new IssueManager(repository);

    private HashSet<String> assignee1 = new HashSet<>((Arrays.asList("Кузин")));
    private HashSet<String> assignee2 = new HashSet<>((Arrays.asList("Иванов")));
    private HashSet<String> assignee3 = new HashSet<>((Arrays.asList("Толстой")));

    private HashSet<String> label1 = new HashSet<>((Arrays.asList("bug")));
    private HashSet<String> label2 = new HashSet<>((Arrays.asList("task")));
    private HashSet<String> label3 = new HashSet<>((Arrays.asList("enhancement")));

    private Issue first = new Issue(1, "Толстой", true, assignee2, "text1", label2, "2019/11/28");
    private Issue second = new Issue(2, "Кузин", false, assignee1, "text2", label1, "2020/06/22");
    private Issue third = new Issue(3, "Кузин", true, assignee3, "text3", label3, "2021/01/10");

    @BeforeEach
    void setUp() {
        repository.add(first);
        repository.add(second);
        repository.add(third);
    }

    @Test
    void shouldCreate() {
        Issue fourth = new Issue(4, "Петров", true, assignee3, "text4", label3, "2021/01/15");
        repository.add(fourth);
        assertArrayEquals(new Issue[]{fourth, third, second, first}, manager.filterBy(issue -> true).toArray());
    }

    @Test
    void shouldUpdateToOpen() {
        repository.openById(2);
        assertArrayEquals(new Issue[]{third, second, first}, manager.filterBy(issue -> issue.isOpen()).toArray());
    }

    @Test
    void shouldUpdateToClose() {
        repository.closeById(1);
        repository.closeById(3);
        assertArrayEquals(new Issue[]{third, second, first}, manager.filterBy(issue -> !issue.isOpen()).toArray());
    }

    @Test
    void shouldFilterByAuthor() {
        assertArrayEquals(new Issue[]{third, second}, manager.filterBy(issue -> issue.getAuthor().equals("Кузин")).toArray());
    }

    @Test
    void shouldFilterByAuthorNotExist() {
        assertArrayEquals(new Issue[]{}, manager.filterBy(issue -> issue.getAuthor().equals("Смирнов")).toArray());
    }

    @Test
    void shouldFilterByLabel() {
        assertArrayEquals(new Issue[]{second}, manager.filterBy(issue -> issue.getLabel().equals(label1)).toArray());
    }

    @Test
    void shouldFilterByLabelNotExist() {
        assertArrayEquals(new Issue[]{}, manager.filterBy(issue -> issue.getLabel().equals(new HashSet<>())).toArray());
    }

    @Test
    void shouldFilterByAssignee() {
        assertArrayEquals(new Issue[]{first}, manager.filterBy(issue -> issue.getAssignee().equals(assignee2)).toArray());
    }

    @Test
    void shouldFilterByAssigneeNotExist() {
        assertArrayEquals(new Issue[]{}, manager.filterBy(issue -> issue.getAssignee().equals(new HashSet<>())).toArray());
    }

    @Test
    void shouldfilterByOpen() {
        assertArrayEquals(new Issue[]{third, first,}, manager.filterBy(issue -> issue.isOpen()).toArray());
    }

    @Test
    void shouldfilterByClose() {
        assertArrayEquals(new Issue[]{second}, manager.filterBy(issue -> !issue.isOpen()).toArray());
    }
}