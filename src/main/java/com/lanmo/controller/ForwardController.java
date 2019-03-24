package com.lanmo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForwardController {

    @RequestMapping(value = "/test")
    public String redirect(){
        return "redirect";
    }

    /**
     * 重定向中传递数据
     * @param model
     * @return
     */
    //url模板  url参数传递
    @GetMapping("/test/redirect")
    public String redirectTo(Model model){
        model.addAttribute("username", "aaa");
        return "redirect:/final/{username}";
    }

    @GetMapping("/final/{username}")
    public String finalPage(@PathVariable String username, Model model){
        System.out.println(username);
        if(!model.containsAttribute("sex")){
        }
        return "final";
    }

    /**
     * flash 属性放入会话中
     * @return
     */
    @RequestMapping(value = "/flash/redirect")
    public String redirectTo(RedirectAttributes model){
        model.addAttribute("username","bbb");
        model.addFlashAttribute("sex","男");
        return "redirect:/final/{username}";
    }

}
