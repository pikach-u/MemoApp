package com.pikachu.memo.controller;

import com.pikachu.memo.repository.MemoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemoController {
    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @GetMapping("/")
    public String listMemos() {
        return "memo-list";
    }

    @PostMapping("/add")
    public String addMemo(
            @RequestParam String title,
            @RequestParam String content
    ) {
        memoRepository.save(title, content);

        return "redirect:/";
    }
}
