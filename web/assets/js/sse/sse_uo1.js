/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var sseSource = 'http://localhost:8080/vsos/sse1';

const eventSource = new EventSource(sseSource);

eventSource.onmessage = function (event) {
    const eventData = event.data;
    // Handle the received data
//    var notiBadge = document.getElementById("noti").innerHTML = eventData;
    console.log(eventData);
    var noti = document.getElementsByClassName("noti");
    for(let i = 0; i < noti.length; i++) {
        noti[i].innerHTML = eventData;
    }
};

eventSource.onerror = function () {
    // Handle any errors
    console.log('An error occurred');
};

