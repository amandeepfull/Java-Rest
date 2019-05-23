

class Ajax {
   
makeRequest(method, url, data){
        return new Promise((resolve, reject) => {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
              if (this.readyState == 4) {
                var resp =   JSON.parse(this.responseText);
                resolve(resp); 
              }
            };
          
          console.log("url : "+url);
            xhttp.open(method, url, true);
          
            if(data){
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send(JSON.stringify(data));
          }else{
          xhttp.send();
          }
    
        })
    }
    }
    
    export default Ajax;