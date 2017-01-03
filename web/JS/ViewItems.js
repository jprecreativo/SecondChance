
var noFilterButton = document.getElementById("noFilterButton");

noFilterButton.onclick = function() {
    
    window.location.reload(false);
};

var filterButton = document.getElementById("filterButton");

filterButton.onclick = function() {
    
    var xhr;
    
    if(window.XMLHttpRequest)   // Navegadores actuales.
        xhr = new XMLHttpRequest();
    
    else if(window.ActiveXObject)   // Para versiones antiguas de IE.
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    
    if (xhr == null)
        alert ("Your browser does not support AJAX!");
        
    else
    {
        var url = "ViewItems.jsp";
        
        xhr.onreadystatechange = getResponse;
        xhr.open("GET", url, false);
        xhr.send(null);
    }
};

function getResponse()
{
    
}
