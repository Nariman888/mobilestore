package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.entities.Commentary;

import java.util.List;

public interface CommentaryService {
    Commentary addCommentary(Commentary commentary);
    List<Commentary> allCommentariesByPhone(Long id);
}
