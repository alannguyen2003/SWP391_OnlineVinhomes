Sơ đồ hoạt động của project:

JSP/JSPX form/links --> (request) controller --> (request) service --> repository (nếu có)
--> (response) service (xử lí logic) --> (response) trả dữ liệu cho controller để đưa lên trình duyệt --> JSP/JSPX

* Các lưu ý:
    - Mỗi một order sẽ chỉ được order 1-2 dịch vụ cùng 1 lúc.
    - Login sẽ bao gồm hai phần: Login using session (setting session for 60 minutes or 2 hours if available)
và login using cookies (Remember me function)
    - Mỗi một service sẽ bao gồm lower price (giá thấp nhất) và upper price (giá cao nhất)
    - Với các controller, dùng chung một kiểu assign to web.xml, KHÔNG ĐƯỢC phép dùng annotation.
    - File SQL sẽ được cung cấp kèm dữ liệu.
    - Sử dụng Design Pattern Singleton.
    - Cho phép sử dụng API nếu cần thiết, nhưng không khuyến khích vì file JSP unstable with API from JS.

* Cấu hình cho project:
    - JDK 17.0.5 (Java Development Kit)
    - Tomcat 10.1.9 
    - SQLJDBC2.0.jar
    - Chuẩn project ANT.
