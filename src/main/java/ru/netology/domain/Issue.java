package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue {
    private int id;
    private String author;
    private boolean open;
    private HashSet<String> assignee;
    private String text;
    private HashSet<String> label;
    private String dateOfChange;
}
