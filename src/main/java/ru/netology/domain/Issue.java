package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Issue {
    private int id;
    private int numberOfComments;
    private boolean isClosed;
    private String author;
    private Set<String> labels;
    private Set<String> assignees;

    public void defineStatus(boolean status) {
        isClosed = status;
    }
}
