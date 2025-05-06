// $(document).ready(function(){
//     $("#editBook").on("submit",function(event){
//         event.preventDefault();
//         var f = $(this).serialize();
//         $.ajax({
//             url: "../editbooks",
//             type: "POST",
//             data: f,
//             success: function(data){
//                 if(data.trim()==="done"){
//                     $("#status").show();
//                     $("#status").removeClass("text-danger");
//                     $("#status").addClass("text-success");
//                     $("#status").html("Book update successfully.");
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
