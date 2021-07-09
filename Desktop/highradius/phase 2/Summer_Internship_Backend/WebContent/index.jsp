<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Highradius</title>
    <link rel="icon" href="images/abc2.png" type="png">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    
    
</head>
<body>

    <div id="header">
        <div id="company-logo">
            <img id="logo_shape" src="images/Group 20399.svg" alt="Company logo"></img>   
        </div>
        
        <img id="hr_logo" src="images/logo.svg" alt="Highradius logo">
    </div>
    
    <p id="invoice">Invoice list</p>
    <div id="table_main">
        <div id="table_body">
            <div id="buttons">
            	<input type="text" id="search" placeholder="Search by Invoice Number">
            	<img id="search_symbol" src="images/search_symbol.svg"></img>
                <button id="delete" onclick="deleteInvoice()"><img id="minus" src="images/Path 18189-1.svg"> Delete</button>
                <button id="edit" onclick="editInvoice()"><img id="plus" src="images/Path 18191.svg"></img> Edit</button>
                <button id="add" onclick="addInvoice()"><img id="plus" src="images/Path 18189.svg"></img> Add</button>
                
            </div>
            <div id="table_content">
				<table>
					<tr>
						<th><label class="cb_label"><input type="checkbox" name="chkDeleteAll" ><span class="checkbox"></span></label></th>
						<th>Customer Name</th>
						<th>Customer #</th>
						<th>Invoice #</th>
						<th>Invoice Amount</th>
						<th>Due Date</th>
						<th>Predicted Payment Date</th>
						<th>Notes</th>
					</tr>
					<tbody>
						<c:forEach var="user" items="${listRow}">
							<tr class="checkBoxClass">
								<td><label class="cb_label"><input type="checkbox" value="${user.invoice_id}" name="chkDelete" class="checkBoxClass"><span class="checkbox"></span></label></td>
								<td><c:out value="${user.name_customer}" /></td>
								<td><c:out value="${user.cust_number}" /></td>
								<td><c:out value="${user.invoice_id}" /></td>
								<td><c:out value="${user.total_open_amount}" /></td>
								<td class="due_in_date"><c:out value="${user.due_in_date}" /></td>
								<td><c:out value="${user.predicted_clear_date}" /></td>
								<td><c:out value="${user.notes}" /></td>							
							</tr>
							
						</c:forEach>					
					</tbody>
				</table>
				
				<div id="no_result">
					<img id="no_result_img" alt="no result" src="images/error_outline-24px.svg"></img>
					<p id="no_result_heading">No results found</p>
					<p id="no_result_para" >Try adjusting your search to find what you are looking for.</p>
					<p id="no_result_para_2">Clear Search</p>
				</div>
			
			
				<div id="edit_invoice">
				<div class="overlay">
					<div id="edit_invoice_header">
					<p id="edit_invoice_heading" >Edit Invoice</p>
					<img id="edit_invoice_close" onclick="editInvoiceclose()" alt="close button" src="images/baseline-close-24px.svg"></img>
					</div>
					<div id="edit_invoice_body">
						<label for="inv_amt" id="edit_invoice_body_p1">Invoice Amount</label>
						<input value="<c:out value='${user.total_open_amount}' />" name="total_open_amount" required type="text" id="invoice_amount_input" placeholder="$10000">
						<label for="inv_notes" id="edit_invoice_body_p2">Notes</label>
						<input value="<c:out value='${user.Notes}' />" name="Notes" type="text" id="invoice_amount_notes_input" placeholder="Loren ipsum...">
					</div>
					<div id="edit_invoice_footer">
						<button id="edit_invoice_cancel" onclick="editInvoiceclose()">Cancel</button>
						<button onClick="getVal()" id="edit_invoice_save" type="submit">Save</button>
						<button id="edit_invoice_reset">Reset</button>
					</div>
				</div>
				</div>
				<div id="delete_invoice">
					<div id="delete_invoice_header">
					<p id="delete_invoice_heading" >Delete record(s)?</p>
					<img id="delete_invoice_close" onclick="deleteInvoiceclose()" alt="close button" src="images/baseline-close-24px.svg"></img>
					</div>
					<div id="delete_invoice_body">
						<p id="delete_invoice_body_p1">You'll lose your record(s) after this action. We can't recover them once you delete.</p>
						<p id="delete_invoice_body_p2">Are you sure you want to <span id="perm_del">permanently delete</span> them?</p>
					</div>
					<div id="delete_invoice_footer">
						<button id="delete_invoice_delete" type="submit">Delete</button>
						<button id="delete_invoice_cancel" onclick="deleteInvoiceclose()">Cancel</button>
					</div>
				</div>

				<div id="add_invoice">
				<form action="insert" method="post">
					<div id="add_invoice_header">
					<p id="add_invoice_heading" >Add Invoice</p>
					<img id="add_invoice_close" onclick="addInvoiceclose()" alt="close button" src="images/baseline-close-24px.svg"></img>
					</div>
					<div id="add_invoice_body">
							<div id="add_invoice_body_left">
								<div class="add_invoice_row">
									<label for="name_customer">Customer Name<span class="req"> *</span></label>
									<input type="text" value="<c:out value='${user.name_customer}' />" id="cname" class="add_invoice_input" name="name_customer" placeholder="Rahul Gandhi" required /><br>
								</div>
								<div class="add_invoice_row">
									<label for="cust_number">Customer No.<span class="req"> *</span></label>
									<input type="text" value="<c:out value='${user.cust_number}' />" id="cno" class="add_invoice_input" name="cust_number" required /><br>
								</div>
								<div class="add_invoice_row">
									<label for="invoice_id">Invoice No.<span class="req"> *</span></label>
									<input type="text" value="<c:out value='${user.invoice_id}' />" id="ino" class="add_invoice_input" name="invoice_id" required /><br>
								</div>
								<div class="add_invoice_row">
									<label for="total_open_amount">Invoice Amount<span class="req"> *</span></label>
									<input type="text" value="<c:out value='${user.total_open_amount}' />" id="ia" class="add_invoice_input" name="total_open_amount" required />
								</div>
							</div>
							<div id="add_invoice_body_right">
								<div class="add_invoice_row">
									<label for="due_in_date">Due Date<span class="req"> *</span></label>
									<input type="date" value="<c:out value='${user.due_in_date}' />" id="dd" class="add_invoice_input" name="due_in_date" required /><br>
								</div>
								<div class="add_invoice_row">	
									<label for="Notes">Notes</label>
									<input id="ta" value="<c:out value='${user.Notes}' />" class="add_invoice_input" name="Notes"/>
								</div>
							</div>
					</div>
					<br>
					<div id="add_invoice_footer">
						<button id="add_invoice_cancel" onclick="addInvoiceclose()">Cancel</button>
						<button id="add_invoice_add" type="submit">Add</button>
						<button id="add_invoice_clear" type="button">Clear</button>
					</div>
					</form>
				</div>
            </div>
        </div>
    </div>
    	
    	<% int pageNo = request.getParameter("pgno")==null?0:Integer.parseInt(request.getParameter("pgno")); %>
		<button id="left_nav" onclick="window.location.href='?pgno=<%=pageNo-1%>'"><img id="left_nav_arrow" alt="left nav" src="images/left-arrow.png"></button>
		<button id="right_nav"onclick="window.location.href='?pgno=<%=pageNo+1%>'"><img id="right_nav_arrow" alt="right nav" src="images/right-arrow.png"></button>
	<div id="overlay" onclick="addInvoiceclose() , editInvoiceclose() , deleteInvoiceclose()"></div>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/script.js"></script>
</body>
</html>