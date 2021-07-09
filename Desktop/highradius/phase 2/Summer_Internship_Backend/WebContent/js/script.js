function addInvoice(){
	document.getElementById('add_invoice').style.display="inline-block";
	document.getElementById("delete").disabled = true;
	document.getElementById("edit").disabled = true;
	document.getElementById("overlay").style.visibility = "visible";
}
function addInvoiceclose(){
	document.getElementById('add_invoice').style.display="none";
	document.getElementById("delete").disabled = false;
	document.getElementById("edit").disabled = false;
	document.getElementById("overlay").style.visibility = "hidden";
}
function editInvoice(){
	document.getElementById('edit_invoice').style.display="inline-block";
	document.getElementById("delete").disabled = true;
	document.getElementById("add").disabled = true;
	document.getElementById("overlay").style.visibility = "visible";
}
function editInvoiceclose(){
	document.getElementById('edit_invoice').style.display="none";
	document.getElementById("delete").disabled = false;
	document.getElementById("add").disabled = false;
	document.getElementById("overlay").style.visibility = "hidden";
}

function deleteInvoice(){
	document.getElementById('delete_invoice').style.display="inline-block";
	document.getElementById("add").disabled = true;
	document.getElementById("edit").disabled = true;
	document.getElementById("overlay").style.visibility = "visible";
}
function deleteInvoiceclose(){
	document.getElementById('delete_invoice').style.display="none";
	document.getElementById("add").disabled = false;
	document.getElementById("edit").disabled = false;
	document.getElementById("overlay").style.visibility = "hidden";
}

function disableNav(){
	document.getElementById('left_nav').disabled = true;
}
function enableNav(){
	document.getElementById('left_nav').disabled = false;
}
function enableNavRight(){
	document.getElementById('right_nav').disabled = false;
}
function disableNavRight(){
	document.getElementById('right_nav').disabled = true;
}

const urlParams = new URLSearchParams(window.location.search);
const myParam = urlParams.get('pgno');

if(myParam==0 || myParam==null){
	disableNav();
}else{
	enableNav();
}
if(myParam*10>=9410){
	disableNavRight();
}else{
	enableNavRight();
}

function oldDates(){
	var dueDates = document.getElementsByClassName('due_in_date'), i, len;
	var ToDate = new Date();
	console.log(ToDate);
	for (i = 0, len = dueDates.length; i < len; i++) {
		var myDate =new Date(dueDates[i].innerHTML);
	    if (myDate < ToDate) {
	    	dueDates[i].style.color = "#ff4646";
	     } 
	}
}
oldDates();

$('input[name="chkDelete"]').click(function () {
    $(this).closest('tr').removeClass('checked');
    if ($(this).is(':checked')) $(this).closest('tr').addClass('checked');
})


$(document).ready(function () {
    $('input[name="chkDeleteAll"]').click(function () {
        $(".checkBoxClass").prop('checked', $(this).prop('checked'));
    });
    
    $(".checkBoxClass").change(function(){
        if (!$(this).prop("checked")){
            $('input[name="chkDeleteAll"]').prop("checked",false);
        }
    });
});

let btnClear = document.getElementById('add_invoice_clear');
let inputs = document.querySelectorAll('input');

btnClear.addEventListener('click', ()=>{
	inputs.forEach(input => input.value = '');
});

let btnClear2 = document.getElementById('edit_invoice_reset');

btnClear2.addEventListener('click', ()=>{
	inputs.forEach(input => input.value = '');
});

$('#delete_invoice_delete').click(function(err){
    $('input[type="checkbox"]:checked').each(function(){ 	
        val = this.value;
        console.log(val);
        var url = "http://localhost:8081/Summer_Internship_Backend/delete?invoice_id=" + val;
        location.href = url;
    })        
})

function getVal(){
	param1 = document.getElementById('invoice_amount_input').value;
	param2 = document.getElementById('invoice_amount_notes_input').value;
	
	$('#edit_invoice_save').click(function(err){
	    $('input[type="checkbox"]:checked').each(function(){ 	
	        val2 = this.value;
	        console.log(val2);
	        var url = "http://localhost:8081/Summer_Internship_Backend/update?invoice_id=" + val2 + "&total_open_amount=" + param1 + "&Notes=" + param2;
	        location.href = url;
	    })        
	})
}



////Fetching dummy json data->
//let fetchData = new Promise((resolve, reject) => {
//		fetch("https://api.npoint.io/ae89e94988e6d78068ee").then(
//		    res=>{
//		        res.json().then(
//		            data=>{
//		            	
//		            	
//		                if (data.length > 0){
//		                	window.tableData = data;
//		                    var temp = "";
//		                   data.forEach((u)=>{
//		         
//		                	    temp += "<tr>";
//		                	    temp += "<td><label class='cb_label'><input type='checkbox'><span class='checkbox'></span></label></td>";
//								temp += "<td>"+u.Cust_Name+"</td>";
//								temp += "<td>"+u.Cust_no+"</td>";
//								temp += "<td>"+u.Invoice_no+"</td>";
//								temp += "<td>"+u.Invoice_amount+"</td>";
//								temp += "<td class='d_date'>"+u.Due_date+"</td>";
//								temp += "<td>"+u.Predicted_payment_date+"</td>";
//								temp += "<td>"+u.Notes+"</td></tr>";
//		                   
//		                   })
//		                    document.getElementById("data1").innerHTML = temp;
//		                   resolve('success!');
//		                }else{
//		                    reject('failed to load data');
//		                }
//		            }
//		        )
//		    } 
//	);
//})
//
//fetchData.then((message) => {
//	var slides = document.getElementsByClassName("d_date");
//	const myJSON = JSON.stringify(slides);
//	for (let i = 0; i < slides.length; i++) {
//		let myDate =new Date(myJSON[i]);
//	    let ToDate = new Date();
//	   
//	    if (myDate <= ToDate) {
//	    	document.getElementsByClassName('d_date')[i].style.color = "red";
//	     } 
//	}
//}
//)

