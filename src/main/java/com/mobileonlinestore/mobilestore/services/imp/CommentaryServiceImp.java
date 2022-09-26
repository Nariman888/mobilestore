package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.entities.Commentary;
import com.mobileonlinestore.mobilestore.repositories.CommentaryRepository;
import com.mobileonlinestore.mobilestore.services.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaryServiceImp implements CommentaryService {
    @Autowired
    private CommentaryRepository commentaryRepository;
    @Override
    public Commentary addCommentary(Commentary commentary) {
        return commentaryRepository.save(commentary);
    }

    @Override
    public List<Commentary> allCommentariesByPhone(Long id) {
        return commentaryRepository.findAllByPhoneId(id);
    }
}
