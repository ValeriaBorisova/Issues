package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.util.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class IssueRepository {
    Collection<Issue> issues = new ArrayList<>();

    public void save(Issue item) {
        issues.add(item);
    }

    public Collection<Issue> findAll() {
        return issues;
    }

    public void saveAll(Collection<Issue> items) {
        issues.addAll(items);
    }

    public Issue findByID(int id) {
        for (Issue item : issues) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void openIssue(int id) throws NotFoundException {
        if (findByID(id) != null) {
            for (Issue item : issues) {
                if (item.getId() == id) {
                    item.defineStatus(false);
                }
            }
        } else {
            throw new NotFoundException("Element with ID " + id + " not found");
        }
    }

    public void closeIssue(int id) throws NotFoundException {
        if (findByID(id) != null) {
            for (Issue item : issues) {
                if (item.getId() == id) {
                    item.defineStatus(true);
                }
            }
        } else {
            throw new NotFoundException("Element with ID " + id + " not found");
        }
    }
}
