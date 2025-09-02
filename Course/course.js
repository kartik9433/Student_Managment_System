const URL = "http://localhost:8080/course/courses";
async function course() {
    const request  =  await fetch(URL);
     const response  =  await request.json();
     console.log(response);
     const  table  = document.querySelector("table");
     response.forEach((val)=>{
        const row  = document.createElement("tr");
             row.innerHTML = `
                 <td>${val.id}</td>
                 <td>${val.name}</td>
                 <td>${val.price}</td>
                 <td>${val.description}</td>
                <td> <button class="btn1">Delete</button></td>
                      `;
         const deletebtn = row.querySelector(".btn1");
        deletebtn.addEventListener("click", async () => {
            const confirmDelete = confirm(`Delete enrollment Id: ${val.id}?`);
            if (!confirmDelete) return;

            try {
                const delres = await fetch(`http://localhost:8080/course/${val.id}`, {
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

 async function addCourse(event){
        event.preventDefault();
        const coursName = document.querySelector(".courseName").value.trim();
        const coursePrice = document.querySelector(".coursePrice").value.trim();
        const courseDes = document.querySelector(".courseDes").value.trim();

        try{
        const request = await fetch("http://localhost:8080/course/create",{
             method:"POST",
             headers:{
                     "Content-Type":"application/json"
             },
              body:JSON.stringify({
                name: coursName,
                price: coursePrice,
                description: courseDes,
                })
        });
        if(request.ok){
             alert("Course added successfully");
            course();
        }
        else{
              alert("failed :"+ request.status);
        }
    }
    catch(error){
          console.log(error);
    }

  }

onload = course;