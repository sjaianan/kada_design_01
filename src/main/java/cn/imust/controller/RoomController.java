package cn.imust.controller;

import cn.imust.domain.*;
import cn.imust.service.BedRoomService;
import cn.imust.service.DormitoryService;
import cn.imust.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BedRoomService bedRoomService;


    @RequestMapping("/roomList")
    public ModelAndView roomList(PageBeanUI pageBeanUI, HttpSession session){

        ModelAndView mv = new ModelAndView();

        User user = (User) session.getAttribute("loginUser");

        List<Room> roomList = roomService.findAll(user);

        mv.addObject("roomList",roomList);

        List<Dormitory> dormitoryList = dormitoryService.findAllByUser(user);
        mv.addObject("dormitoryList",dormitoryList);

        if (pageBeanUI.getRoom() != null){
            System.out.println(pageBeanUI.getRoom().getDormitory().getUser().getName());
        }

        mv.setViewName("forward:/jsp/room/room.jsp");

        return mv;
    }

    @RequestMapping("/addRoom")
    public String addRoom(Room room){
        //添加宿舍
        roomService.addRoom(room);
        //添加成功后扩展6个床位
        BedRoom bedRoom = new BedRoom();
        bedRoomService.addBedRoom(bedRoom,room);

        return "redirect:roomList";
    }

    @RequestMapping("/addRoomUI")
    public ModelAndView addRoomUI(HttpSession session){
        ModelAndView mv = new ModelAndView();

        User user = (User) session.getAttribute("loginUser");
        List<Dormitory> dormitoryList = null;
        if ("1".equals(user.getStatus())){
            dormitoryList = dormitoryService.findAll();
        }else {
            dormitoryList= dormitoryService.findAllByUser(user);
        }
        mv.addObject("dormitoryList",dormitoryList);
        mv.setViewName("forward:/jsp/room/showAddRoom.jsp");

        return mv;
    }

}
