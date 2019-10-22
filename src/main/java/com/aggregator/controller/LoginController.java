package com.aggregator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер предназначен для отображения
 * страницы авторизации пользователя.
 */
@Controller
public class LoginController {

    /**
     * Метод для обработки GET - запроса.
     * @param model модель
     * @param error строка с ошибками из запроса,
     *              поялвяется при неправильном вводе
     *              логина или пароля
     * @param logout строка появляется при выходе пользователя
     * @return представление страницы авторизации
     */
    @RequestMapping(name = "/login", method = RequestMethod.GET)
    public String log(final Model model,
                      final String error,
                      final String logout) {

        if (error != null) {
            model.addAttribute("error", "Неправильный логин или пароль.");
        }
        if (logout != null) {
            model.addAttribute("message", "Успешный выход");
        }
        model.addAttribute("anon", true);
        return "login";
    }

}
