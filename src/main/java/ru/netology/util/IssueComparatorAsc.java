package ru.netology.util;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class IssueComparatorAsc implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getNumberOfComments() - o1.getNumberOfComments();
    }
}
