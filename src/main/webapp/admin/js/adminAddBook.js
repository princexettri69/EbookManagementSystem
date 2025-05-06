// $(document).ready(function(){
//     $("#addBook").on("submit",function(event){
//         event.preventDefault();
//         var form = $("#addBook")[0];
//         var formData = new FormData(form);
//         console.log(formData);
//         $.ajax({
//             url: "/add_books",
//             type: "POST",
//             data: formData,
//             contentType: false,
//             processData: false,
//             success: function(data){
//                 if(data.trim()==="done"){
//                     $("#status").show();
//                     $("#status").removeClass("text-danger");
//                     $("#status").addClass("text-success");
//                     $("#status").html("Book add successfully.");
//                     $("#bookName").val("");
//                     $("#authorName").val("");
//                     $("#price").val("");
//                     $("#totalBook").val("");
//                     $("#category").val("new");
//                     $("#photo").val("");
//
//                 } else{
//                     $("#status").show();
//                     $("#status").removeClass("text-success");
//                     $("#status").addClass("text-danger");
//                     $("#status").html("Something went wrong.");
//                 }
//             },
//             error: function(){
//                 $("#status").show();
//                 $("#status").removeClass("text-success");
//                 $("#status").addClass("text-danger");
//                 $("#status").html("Something went wrong, please try again later.");
//             }
//         });
//     });
// });
