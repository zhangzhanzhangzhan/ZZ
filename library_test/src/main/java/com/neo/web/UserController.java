package com.neo.web;
import com.github.pagehelper.PageHelper;
import com.neo.entity.*;
import com.neo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PageHelper pageHelper;

    @Autowired
    private UserMapper userMapper;

    //获取书本列表
    @RequestMapping("/showBookList")
    public List<Book> showBookList(Book book,HttpSession session) {
        Map<String,Object> map=new ConcurrentHashMap<>();
        List<Book> bookList=userMapper.showBookList(book);
        return bookList;
    }
    //用户登录
    @RequestMapping("/dologin")
    public Map<String,Object> login(User user, HttpServletRequest request, HttpSession session) {
        user=userMapper.getOneByNameAndPass(user);
        Map<String,Object> map=new ConcurrentHashMap<>();
        if (user!=null){
            map.put("code",200);
            map.put("message","登录成功");
            saveLoginStatus(request,user);
        }else {
            map.put("code",404);
            map.put("message","用户名密码错误");
        }
        return map;
    }
    //管理员登录
    @RequestMapping("/dologinManager")
    public Map<String,Object> dologinManager(Manager manager, HttpServletRequest request, HttpSession session) {
        manager=userMapper.getManager(manager);
        Map<String,Object> map=new ConcurrentHashMap<>();
        if (manager!=null){
            map.put("code",200);
            map.put("message","登录成功");
        }else {
            map.put("code",404);
            map.put("message","用户名密码错误");
        }
        return map;
    }
    //保存登陆信息
    public void saveLoginStatus(HttpServletRequest request, User student) {
        HttpSession session=request.getSession();
        session.setAttribute("userSession",student);
        student=(User) session.getAttribute("userSession");
        String studentID=student.getUserID();
        System.out.println(studentID);
        session.setMaxInactiveInterval(7*24*60*60);
    }
    //用户注册
    @RequestMapping("/register")
    public Map<String,Object> register(User user,HttpServletRequest request,HttpSession session) {
        List<User> userList=userMapper.getRegister(user);
        Map<String,Object> map=new ConcurrentHashMap<>();
        if (userList.size()==0){
            userMapper.insert(user);
            map.put("code",200);
            map.put("message","注册成功");
        }else {
            map.put("code",404);
            map.put("message","改用户已存在");
        }
        return map;
    }
    //收藏
    @RequestMapping("/collect")
    public Map<String,Object> collect(Collect collect, HttpSession session){

        Map<String,Object> map=new ConcurrentHashMap<>();
        User user=(User)session.getAttribute("userSession");
        if (null==user){
            map.put("code",500);
            return map;
        }
        collect.setUserID(user.getUserID());
        List<Collect> collectList=userMapper.getCollect(collect);
        if (collectList.size()>0){
            map.put("code",0);
            return map;
        }
        int count=userMapper.collect(collect);
        if (1!=count){
            map.put("code",404);
        }else {
            map.put("code",200);
        }
        return map;
    }
    //显示收藏列表
    @RequestMapping("/showCollectList")
    public List<Collect> showCollectList(Collect collect,HttpSession session) {
        Map<String,Object> map=new ConcurrentHashMap<>();
        User user=(User)session.getAttribute("userSession");
        collect.setUserID(user.getUserID());
        List<Collect> collectList=userMapper.showCollectList(collect);
        return collectList;
    }
    //删除收藏
    @RequestMapping("/deleteCollect")
    public Map<String,Object> deleteCollect(Collect collect) {
        userMapper.deleteCollect(collect);
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("code",200);
        return map;
    }
    //借书
    @RequestMapping("/borrow")
    public Map<String,Object> borrow(Borrow borrow, HttpSession session){
        Map<String,Object> map=new ConcurrentHashMap<>();
        User user=(User)session.getAttribute("userSession");
        if (null==user){
            map.put("code",500);
            return map;
        }
        borrow.setUserID(user.getUserID());
        List<Borrow> borrowList=userMapper.getBorrow(borrow);
        if (borrowList.size()>0){
            map.put("code",0);
            return map;
        }
        Book book=new Book();
        book.setBookID(borrow.getBookID());
        book.setAmount(-1);
        userMapper.editBookAmount(book);
        int count=userMapper.borrow(borrow);
        if (1!=count){
            map.put("code",404);
        }else {
            map.put("code",200);
        }
        return map;
    }
    //显示借阅列表
    @RequestMapping("/showBorrowList")
    public List<Borrow> showBorrowList(Borrow borrow,HttpSession session) {
        Map<String,Object> map=new ConcurrentHashMap<>();
        User user=(User)session.getAttribute("userSession");
        borrow.setUserID(user.getUserID());
        List<Borrow> borrowList=userMapper.showBorrowList(borrow);
        return borrowList;
    }
    //显示用户列表
    @RequestMapping("/showUserList")
    public List<User> showUserList(User user) {
        List<User> userList=userMapper.showUserList(user);
        return userList;
    }
    //删除用户
    @RequestMapping("/deleteUser")
    public Map<String,Object> deleteUser(User user) {
        userMapper.deleteUser(user);
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("code",200);
        return map;
    }
    //删除书籍
    @RequestMapping("/deleteBook")
    public Map<String,Object> deleteBook(Book book) {
        userMapper.deleteBook(book);
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("code",200);
        return map;
    }
    //添加书籍
    @RequestMapping("/addBook")
    public Map<String,Object> addBook(Book book) {
        userMapper.addBook(book);
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("code",200);
        return map;
    }
    //显示图书馆员列表
    @RequestMapping("/showManagerList")
    public List<Manager> showManagerList(Manager manager) {
        List<Manager> managerList=userMapper.showManagerList(manager);
        return managerList;
    }
    //删除图书馆员
    @RequestMapping("/deleteManager")
    public Map<String,Object> deleteManager(Manager manager) {
        userMapper.deleteManager(manager);
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("code",200);
        return map;
    }
    //新增图书馆员
    @RequestMapping("/registerManager")
    public Map<String,Object> registerManager(Manager manager,HttpServletRequest request,HttpSession session) {
        List<Manager> managerList=userMapper.getRegisterMa(manager);
        Map<String,Object> map=new ConcurrentHashMap<>();
        if (managerList.size()==0){
            userMapper.insertManager(manager);
            map.put("code",200);
            map.put("message","注册成功");
        }else {
            map.put("code",404);
            map.put("message","改用户已存在");
        }
        return map;
    }
    //新增预定
    @RequestMapping("/addBooking")
    public Map<String,Object> addBooking(Booking booking,HttpSession session) {
        Map<String,Object> map=new ConcurrentHashMap<>();
        User user=(User)session.getAttribute("userSession");
        if (null==user){
            map.put("code",500);
            return map;
        }
        booking.setUserID(user.getUserID());
        List<Booking> bookingList=userMapper.getBooking(booking);
        if (bookingList.size()>0){
            map.put("code",0);
            return map;
        }
        Book book=new Book();
        book.setBookID(booking.getBookID());
        book.setAmount(-1);
        userMapper.editBookAmount(book);
        int count=userMapper.addBooking(booking);
        if (1!=count){
            map.put("code",404);
        }else {
            map.put("code",200);
        }
        return map;
    }
    //显示预定列表
    @RequestMapping("/showBookingList")
    public List<Booking> showBookingList(Booking booking,HttpSession session) throws ParseException {
        Map<String,Object> map=new ConcurrentHashMap<>();
        User user=(User)session.getAttribute("userSession");
        booking.setUserID(user.getUserID());
        List<Booking> bookingList=userMapper.showBookingList(booking);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        for (Booking booking1 : bookingList) {
            Date date=sdf.parse(booking1.getBookingTime());
           c.setTime(date);
           c.add(Calendar.DAY_OF_MONTH, 1);
           Date date1=c.getTime();
           if (new Date().after(date1)){
               booking1.setState("已失效");
           }else {
               booking1.setState("已生效");
           }
        }
        return bookingList;
    }
    //显示预定列表
    @RequestMapping("/editUser")
    public Map<String,Object> editUser(User user,HttpSession session) {
        Map<String,Object> map=new ConcurrentHashMap<>();
        User user1=(User)session.getAttribute("userSession");
        user.setUserID(user1.getUserID());
        userMapper.editUser(user);
        map.put("code",200);
        return map;
    }
}