const URL = "http://localhost:8080/stuCon/allStudent";
async function student() {
    const request  =  await fetch(URL);
     const response  =  await request.json();
     console.log(response);
     const  table  = document.querySelector("table");
     response.forEach((val)=>{
        const row  = document.createElement("tr");
             row.innerHTML = `
                 <td>${val.id}</td>
                 <td>${val.name}</td>
                 <td>${val.email}</td>
                 <td><button class="btn1">Delete</button><td>
                      `;
       const deletebtn = row.querySelector(".btn1");
        deletebtn.addEventListener("click", async () => {
            const confirmDelete = confirm(`Delete enrollment Id: ${val.id}?`);
            if (!confirmDelete) return;

            try {
                const delres = await fetch(`http://localhost:8080/stuCon/${val.id}`, {
                    method: "DELETE"
                });

                if (delres.ok) {
                    row.remove();
                    alert("Deleted");
                } else {
                    alert("Failed to delete, status: " + delres.status);
                }
            } catch (error) {
                console.error("Error:", error);
            }
        });
           table.appendChild(row);
     });
}   


    async  function addStudent(event){
        event.preventDefault();
    const name = document.querySelector(".name").value.trim()
    const email = document.querySelector(".email").value.trim();
   const phone = document.querySelector(".phone").value.trim();
        if(!name|| !email|| !phone){
                  alert("something is missing");
                  return;
        }
        try{
             const request = await fetch("http://localhost:8080/stuCon/save",{
                method:"POST",
                headers:{
                        "Content-Type":"application/json"
                },
                body:JSON.stringify({
                    name:name,
                    email:email,
                    phone:phone
                })
             });
             if(request.ok){
                alert("Student Added Sucessfully");
                student();
             }
             else{
                 alert("Failed to Add Student Status:" + request.status);
             }
            }
            catch(error){
                console.log("error :"+error);
            }
      }


window.onload = student
