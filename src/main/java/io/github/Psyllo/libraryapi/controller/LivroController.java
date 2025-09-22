package io.github.Psyllo.libraryapi.controller;

import io.github.Psyllo.libraryapi.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class LivroController {

    private final LivroService livroService;

}
