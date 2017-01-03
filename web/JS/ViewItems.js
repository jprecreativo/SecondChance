
var noFilterButton = document.getElementById("noFilterButton");

noFilterButton.onclick = function() {
    
    window.location.reload(false);
};

var filterButton = document.getElementById("filterButton"), xhr;

filterButton.onclick = function() {
    
    if(window.XMLHttpRequest)   // Current browsers.
        xhr = new XMLHttpRequest();
    
    else if(window.ActiveXObject)   // Old IE versions.
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    
    if (xhr == null)
        alert ("Your browser does not support AJAX!");
        
    else
    {
        var url = "ViewItems";
        
        xhr.onreadystatechange = getResponse;
        xhr.open("GET", url, false);
        xhr.send(null);
    }
};

function getResponse()
{
    if(xhr.readyState == 4 && xhr.status == 200)   // Si la petición ha finalizado correctamente.
    {
        // alert("You have pressed filter button.");
        
        
    }
}
