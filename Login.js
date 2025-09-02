const btn  = document.querySelector("#login");
btn.addEventListener("click", async (event)=>{
  event.preventDefault();
  try{
    const username = document.querySelector(".name").value;
    const password = document.querySelector(".password").value;
     
     const request = await fetch(`http://localhost:8080/jwt/gen_tok?username=${username}&password=${password}`, {
        method:"POST",
       });
        const data = await request.text();
        localStorage.setItem("jwt",data);
        console.log(data);

        const token = localStorage.getItem("jwt");
        console.log(token);
      
         const  request1 = await fetch("http://localhost:8080/jwt/validate",{
            method:"GET",
           headers:{
              "Authorization": `Bearer ${token}`
           }
         });
         
         const response = await request1.text();

         console.log(response);
         if(response==="valid"){
           alert("Successfully logged in!");  
           window.location.href="page.html";
          
         }
         else{
           alert("Login failed. Try again.");
            console.log("login failed");
         }
      }
      catch(error ){
         alert("Something went wrong. Check console for details.");
      }
});
