/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var sseSource = 'http://localhost:8080/vsos/sse1?bId=';
var blockId = '' + document.getElementById("block").value;

const eventSource = new EventSource(sseSource+blockId);

eventSource.onmessage = function (event) {
    const eventData = event.data;
    // Handle the received data
    document.getElementById("noti").innerHTML = eventData;
    console.log(eventData);
};

eventSource.onerror = function () {
    // Handle any errors
    console.log('An error occurred');
};

