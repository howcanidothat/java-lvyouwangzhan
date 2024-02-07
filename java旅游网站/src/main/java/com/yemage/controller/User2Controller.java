package com.yemage.controller;


import com.github.pagehelper.PageInfo;
import com.yemage.domain.*;
import com.yemage.service.CategoryService;
import com.yemage.service.RouteImgService;
import com.yemage.service.RouteService;
import com.yemage.service.SellerService;
import com.yemage.service.impl.UserAdminService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class User2Controller {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/user/category/list")
    public String find(Model model) {
        List<Category> list = categoryService.find();
        model.addAttribute("list", list);
        return "user1/user_category_list";
    }

    @Controller
    public class RouteController {

        @Autowired
        private RouteService routeService;
        @Autowired
        private CategoryService categoryService;
        @Autowired
        private SellerService sellerService;

        @Autowired
        private RouteImgService imgService;

        /**
         * 分页
         *
         * @param route
         * @param pageNum
         * @param pageSize
         * @param model
         * @return
         */
        @RequestMapping("/user/route/page")
        public String page(
                Route route,
                @RequestParam(defaultValue = "1") Integer pageNum,
                @RequestParam(defaultValue = "10") Integer pageSize,
                Model model) {
            PageInfo<Route> page = routeService.findPage(route, pageNum, pageSize);
            model.addAttribute("page", page);

            //查询所有分类生成下拉框
            List<Category> categories = categoryService.find();
            model.addAttribute("categories", categories);
            List<Seller> sellers = sellerService.find(new Seller());
            model.addAttribute("sellers", sellers);

            //用于页面回显
            model.addAttribute("route", route);

            return "user1/user_route_list";
        }
    }
    @Controller
    @RequestMapping("/user/seller/")
    public class SellerController {

        @Autowired
        private SellerService sellerService;

        @RequestMapping("/page")
        public String page(
                Seller seller,
                @RequestParam(defaultValue = "1") Integer pageNum,
                @RequestParam(defaultValue = "10") Integer pageSize,
                Model model) {
            PageInfo<Seller> page = sellerService.findPage(seller, pageNum, pageSize);
            model.addAttribute("page", page);

            return "user1/user_seller_list";
        }
    }

    @Autowired
    private RouteService routeService;

    @RequestMapping("/user/route/toimage/{id}")
    public String toImage(@PathVariable("id") Integer id, Model model) {

        Route route = routeService.findById(id);
        model.addAttribute("route", route);

        return "user1/user_image_list";
    }

    @RequestMapping("/user/route/doimage")
    public String doImage(
            Integer rid,
            @RequestParam("bigPicFile") MultipartFile[] bigPicFile,
            @RequestParam("smallPicFile")MultipartFile[] smallPicFile,
            HttpServletRequest request) throws  Exception {

        List<String> bigPic = new ArrayList<>();
        List<String> smallPic = new ArrayList<>();

        String path = request.getServletContext().getRealPath("/");
        for (MultipartFile f : bigPicFile) {
            File bigPath = new File(path + "img\\product\\big-pic\\");
            if (!bigPath.exists()) {
                bigPath.mkdirs();
            }
            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + FilenameUtils.getExtension(f.getOriginalFilename());
            f.transferTo(new File(bigPath, fileName));
            bigPic.add("img/product/big-pic/" + fileName);
        }

        for (MultipartFile f : smallPicFile) {
            File smallPath = new File(path + "img\\product\\small-pic\\");
            if (!smallPath.exists()) {
                smallPath.mkdirs();
            }
            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + FilenameUtils.getExtension(f.getOriginalFilename());
            f.transferTo(new File(smallPath, fileName));
            smallPic.add("img/product/small-pic/" + fileName);
        }

        //要添加的图片列表
        List<RouteImg> ris = new ArrayList<>();
        for (int i=0; i<bigPic.size(); i++) {
            RouteImg img = new RouteImg();
            img.setRid(rid);
            img.setBigpic(bigPic.get(i));
            img.setSmallpic(smallPic.get(i));
            ris.add(img);
        }


        return  "redirect:/user/route/page";
    }

}