<%--<%@ page import="com.example.resourceTrackPro.entities.Equipment" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<%@ page import="com.example.resourceTrackPro.entities.User" %>--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.resourceTrackPro.entities.Equipment" %>
<%
    if (session.getAttribute("name") == null){
        System.out.println("You are not loged in ?");
        response.sendRedirect("../login.jsp");
    }
%>


<!DOCTYPE html>
<!-- Coding by CodingNepal | www.codingnepalweb.com -->
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Dashboard </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--    bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<%--    tailwind--%>
    <script src="https://cdn.tailwindcss.com"></script>

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tw-elements/dist/css/tw-elements.min.css" />
    <style>
        input[type="datetime-local"] {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            width: 200px;
            margin: 0.5rem;
        }
        /* The container must be positioned relative: */
        .custom-select {
            position: relative;
            font-family: Arial;
            border-radius: initial;
        }

        .custom-select select {
            display: none; /*hide original SELECT element: */
        }

        .select-selected {
            background-color: DodgerBlue;
        }

        /* Style the arrow inside the select element: */
        .select-selected:after {
            position: absolute;
            content: "";
            top: 14px;
            right: 10px;
            width: 0;
            height: 0;
            border: 6px solid transparent;
            border-color: #fff transparent transparent transparent;
        }

        /* Point the arrow upwards when the select box is open (active): */
        .select-selected.select-arrow-active:after {
            border-color: transparent transparent #fff transparent;
            top: 7px;
        }

        /* style the items (options), including the selected item: */
        .select-items div,.select-selected {
            color: #ffffff;
            padding: 8px 16px;
            border: 1px solid transparent;
            border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
            cursor: pointer;
            border-radius: 2rem;
        }

        /* Style items (options): */
        .select-items {
            position: absolute;
            background-color: DodgerBlue;
            top: 100%;
            left: 0;
            right: 0;
            z-index: 99;
            border-radius: 2rem;
        }

        /* Hide the items when the select box is closed: */
        .select-hide {
            display: none;
        }

        .select-items div:hover, .same-as-selected {
            background-color: rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body id="DOMContentLoaded">

<div class="sidebar">
    <div class="logo-details">
        <i class='bx bxl-c-plus-plus'></i>
        <span class="logo_name">ResourceTrackPro</span>
    </div>
    <ul class="nav-links">
        <li>
            <a href="#" class="active">
                <i class='bx bx-grid-alt' ></i>
                <span class="links_name">Dashboard</span>
            </a>
        </li>
<%--        <li>--%>
<%--            <a href="#">--%>
<%--                <i class='bx bx-box' ></i>--%>
<%--                <span class="links_name">Product</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="#">--%>
<%--                <i class='bx bx-list-ul' ></i>--%>
<%--                <span class="links_name">Order list</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="#">--%>
<%--                <i class='bx bx-pie-chart-alt-2' ></i>--%>
<%--                <span class="links_name">Analytics</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="#">--%>
<%--                <i class='bx bx-coin-stack' ></i>--%>
<%--                <span class="links_name">Stock</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="#">--%>
<%--                <i class='bx bx-book-alt' ></i>--%>
<%--                <span class="links_name">Total order</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="#">--%>
<%--                <i class='bx bx-user' ></i>--%>
<%--                <span class="links_name">Team</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="#">--%>
<%--                <i class='bx bx-message' ></i>--%>
<%--                <span class="links_name">Messages</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="#">--%>
<%--                <i class='bx bx-heart' ></i>--%>
<%--                <span class="links_name">Favrorites</span>--%>
<%--            </a>--%>
<%--        </li>--%>
        <li>
            <a href="#">
                <i class='bx bx-cog' ></i>
                <span class="links_name">Profile</span>
            </a>
        </li>
        <li class="log_out">
            <a href="${pageContext.request.contextPath}/login.jsp">
                <i class='bx bx-log-out'></i>
                <span class="links_name">Log out</span>
            </a>
        </li>
    </ul>
</div>


<section class="home-section">
    <nav>
        <div class="sidebar-button">
            <i class='bx bx-menu sidebarBtn'></i>
            <span class="dashboard">Dashboard</span>
        </div>
        <div class="search-box">
            <input type="text" placeholder="Search...">
            <i class='bx bx-search' ></i>
        </div>
        <div class="profile-details">
            <img src="images/profile.jpg" alt="">
            <span class="admin_name">abdelaziz</span>
            <i class='bx bx-chevron-down' ></i>
        </div>
    </nav>

    <div class="home-content m-2">
<%--        statistic--%>
        <div class="overview-boxes">
            <div class="box">
                <div class="right-side">
                    <div class="box-topic">Total Order</div>
                    <div class="number">40,876</div>
                    <div class="indicator">
                        <i class='bx bx-up-arrow-alt'></i>
                        <span class="text">Up from yesterday</span>
                    </div>
                </div>
                <i class='bx bx-cart-alt cart'></i>
            </div>
            <div class="box">
                <div class="right-side">
                    <div class="box-topic">Total Sales</div>
                    <div class="number">38,876</div>
                    <div class="indicator">
                        <i class='bx bx-up-arrow-alt'></i>
                        <span class="text">Up from yesterday</span>
                    </div>
                </div>
                <i class='bx bxs-cart-add cart two' ></i>
            </div>
            <div class="box">
                <div class="right-side">
                    <div class="box-topic">Total Profit</div>
                    <div class="number">$12,876</div>
                    <div class="indicator">
                        <i class='bx bx-up-arrow-alt'></i>
                        <span class="text">Up from yesterday</span>
                    </div>
                </div>
                <i class='bx bx-cart cart three' ></i>
            </div>
            <div class="box">
                <div class="right-side">
                    <div class="box-topic">Total Return</div>
                    <div class="number">11,086</div>
                    <div class="indicator">
                        <i class='bx bx-down-arrow-alt down'></i>
                        <span class="text">Down From Today</span>
                    </div>
                </div>
                <i class='bx bxs-cart-download cart four' ></i>
            </div>
        </div>
<%--        cards--%>
        <div class="card-list flex">
            <div class="card m-2" style="width: 18rem;">
                <img src="${pageContext.request.contextPath}/images/chair.jpeg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">chair</h5>
                    <form action="${pageContext.request.contextPath}/GetEquipment" method="post">
                        <p>la date de retourne :</p>
                        <input type="hidden" name="equipmentId" value="1">
                        <input type="datetime-local" name="dateTime" step="1" required>
                        <input type="submit" class="py-2 px-4 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75"  value="réserver">
                    </form>
                </div>
            </div>
            <div class="card m-2" style="width: 18rem;">
                <img src="${pageContext.request.contextPath}/images/img_1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Office</h5>
                    <form action="${pageContext.request.contextPath}/GetEquipment" method="post">
                        <p>la date de reteurne</p>
                        <input type="hidden" name="equipmentId" value="2">
                        <input type="datetime-local" name="dateTime"  step="1" required>
                        <input type="submit" class="py-2 px-4 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75" value="réserver">
                    </form>

                </div>
            </div>
            <div class="card m-2" style="width: 18rem;">
                <img src="${pageContext.request.contextPath}/images/img_2.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Laptop</h5>
                    <form action="${pageContext.request.contextPath}/GetEquipment" method="post">
                        <p>la date de reteurne</p>
                        <input type="hidden" name="equipmentId" value="3">
                        <input type="datetime-local" name="dateTime"  step="1" required>
                        <input type="submit"  class="py-2 px-4 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75" value="réserver">
                    </form>

                </div>
            </div>
        </div>
    <%--        reservation cards--%>
        <div >
            <h1>les equipment deja reservee </h1>
            <%--<c:forEach items="${reservedEquipments}" var="equipment">
                <li>${equipment.getName()}</li>
            </c:forEach>--%>

            <div class="card-list flex">
                <%
                    List<Equipment> reservedEquipments = (List<Equipment>) request.getAttribute("reservedEquipments");
                    System.out.println("dashboard ----->"+ reservedEquipments);
                    for (Equipment resEqui : reservedEquipments) {
                %>
                <div class="card m-2" style="width: 18rem;">
                    <img src="${pageContext.request.contextPath}/images/chair.jpeg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title"><%resEqui.getName();%></h5>
                        <form action="../GetEquipment" method="post">
                            <p>la date de retourne :</p>
                            <input type="hidden" name="equipmentId" value="<%resEqui.getId();%>">
                            <input type="datetime-local" name="dateTime" step="1" value="" required>
                            <input type="submit" class="py-2 px-4 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75"  value="réserver">
                        </form>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</section>

<script>

        document.getElementById("dateTimePicker").addEventListener("change", function() {
        var selectedDateTime = this.value.replace("T", " ");
        this.value = selectedDateTime;
    });

    // Add an event listener for the "focus" event on the window object
    window.addEventListener('focus', function () {
        // Reload the page when it gains focus
        location.reload();

    });
    let sidebar = document.querySelector(".sidebar");
    let sidebarBtn = document.querySelector(".sidebarBtn");
    sidebarBtn.onclick = function() {
        sidebar.classList.toggle("active");
        if(sidebar.classList.contains("active")){
            sidebarBtn.classList.replace("bx-menu" ,"bx-menu-alt-right");
        }else
            sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
    }
//     to drop down
        var x, i, j, l, ll, selElmnt, a, b, c;
        /*look for any elements with the class "custom-select":*/
        x = document.getElementsByClassName("custom-select");
        l = x.length;
        for (i = 0; i < l; i++) {
        selElmnt = x[i].getElementsByTagName("select")[0];
        ll = selElmnt.length;
        /*for each element, create a new DIV that will act as the selected item:*/
        a = document.createElement("DIV");
        a.setAttribute("class", "select-selected");
        a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
        x[i].appendChild(a);
        /*for each element, create a new DIV that will contain the option list:*/
        b = document.createElement("DIV");
        b.setAttribute("class", "select-items select-hide");
        for (j = 1; j < ll; j++) {
        /*for each option in the original select element,
        create a new DIV that will act as an option item:*/
        c = document.createElement("DIV");
        c.innerHTML = selElmnt.options[j].innerHTML;
        c.addEventListener("click", function(e) {
        /*when an item is clicked, update the original select box,
        and the selected item:*/
        var y, i, k, s, h, sl, yl;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        sl = s.length;
        h = this.parentNode.previousSibling;
        for (i = 0; i < sl; i++) {
        if (s.options[i].innerHTML == this.innerHTML) {
        s.selectedIndex = i;
        h.innerHTML = this.innerHTML;
        y = this.parentNode.getElementsByClassName("same-as-selected");
        yl = y.length;
        for (k = 0; k < yl; k++) {
        y[k].removeAttribute("class");
    }
        this.setAttribute("class", "same-as-selected");
        break;
    }
    }
        h.click();
    });
        b.appendChild(c);
    }
        x[i].appendChild(b);
        a.addEventListener("click", function(e) {
        /*when the select box is clicked, close any other select boxes,
        and open/close the current select box:*/
        e.stopPropagation();
        closeAllSelect(this);
        this.nextSibling.classList.toggle("select-hide");
        this.classList.toggle("select-arrow-active");
    });
    }
        function closeAllSelect(elmnt) {
        /*a function that will close all select boxes in the document,
        except the current select box:*/
        var x, y, i, xl, yl, arrNo = [];
        x = document.getElementsByClassName("select-items");
        y = document.getElementsByClassName("select-selected");
        xl = x.length;
        yl = y.length;
        for (i = 0; i < yl; i++) {
        if (elmnt == y[i]) {
        arrNo.push(i)
    } else {
        y[i].classList.remove("select-arrow-active");
    }
    }
        for (i = 0; i < xl; i++) {
        if (arrNo.indexOf(i)) {
        x[i].classList.add("select-hide");
    }
    }
    }
        /*if the user clicks anywhere outside the select box,
        then close all select boxes:*/
        document.addEventListener("click", closeAllSelect);


</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<%--<script src="https://cdn.jsdelivr.net/npm/tw-elements/dist/js/tw-elements.umd.min.js"></script>--%>
</body>
</html>