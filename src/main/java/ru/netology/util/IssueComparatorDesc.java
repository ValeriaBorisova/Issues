package ru.netology.util;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class IssueComparatorDesc implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getNumberOfComments() - o2.getNumberOfComments();
    }
}
