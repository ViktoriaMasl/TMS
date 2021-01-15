package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepo {
    private List<Issue> items = new ArrayList<>();

    public List<Issue> getAll() {
        return items;
    }

    public void add(Issue item) {
        items.add(item);
    }

    public void openById(int id) {
        for (Issue issue : getAll()) {
            if (issue.getId() == id) {
                issue.setOpen(true);
                break;
            }
        }
    }

    public void closeById(int id) {
        for (Issue issue : getAll()) {
            if (issue.getId() == id) {
                issue.setOpen(false);
                break;
            }
        }
    }
}
