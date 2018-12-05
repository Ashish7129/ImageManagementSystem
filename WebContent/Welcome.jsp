<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="index.jsp"%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache");
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("LoginPage.jsp");
	}
%>
<div class="container">
	<nav class="navbar navbar-default">
		<ul class="nav navbar-nav navbar-left">
			<li><h4><%=session.getAttribute("currentUser")%>,
				</h4></li>
			<li><form action="Logout.jsp" method="post">
					<button type="submit" class="btn btn-primary">Logout</button>
				</form></li>
		</ul>
	</nav>
	<div class="jumbotron">
		<form action="saveImage" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>Enter the Image Name:</label> <input type="text"
					name="imageName" class="form-control" required>
			</div>
			<div class="form-group">
				<label>Upload the Image:</label> <input type="file" name="image"
					value="Upload.." accept="image/jpg, image/jpeg, image/png"
					class="form-control" required />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-default"
					onclick="this.form.reset()">Clear</button>
			</div>
		</form>
		<!-- Success -->
		<div class="text-success">
			<%
				if (Boolean.parseBoolean(request.getParameter("SaveSuccess"))) {
					out.println("Image Save Successfully");
				}
				if (Boolean.parseBoolean(request.getParameter("UpdateSuccess"))) {
					out.println("Image Updated Successfully");
				}
			%>
		</div>
		<!-- Errors -->
		<div class="text-danger">
			<%
				if (Boolean.parseBoolean(request.getParameter("IsNullImageDetails"))) {
					out.println(Constants.IMAGE_DETAILS_NULL);
				}
				if (Boolean.parseBoolean(request.getParameter("invalidImageType"))) {
					out.println(Constants.INVALID_IMAGE_TYPE);
				} else if (Boolean.parseBoolean(request.getParameter("invalidImageSize"))) {
					out.println(Constants.SIZE_LIMIT_EXCEED);
				} else if (Boolean.parseBoolean(request.getParameter("invalidTotalImagesSize"))) {
					out.println(Constants.TOTALSIZE_LIMIT_EXCEED);
				}
			%>
		</div>
	</div>

	<!-- Show images -->
	<div class="table-responsive">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>Image No</th>
					<th>Image Name</th>
					<th>Size</th>
					<th>Picture</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="image" items="${imageList}">
					<tr>
						<td>${image.imageId}</td>
						<td>${image.imageName}</td>
						<td>${Math.round(image.imageSize/1024)}KB</td>
						<td><img src="<c:url value='/images/${image.imagePath}'/>"
							height="160px" width="150px" /></td>

						<td>
							<nav class="navbar">
								<ul class="nav navbar-nav">
									<li><form action="EditImage" method="post">
											<button type="submit" value="${image.imageId}"
												name="editImage" class="btn btn-primary a-btn-slide-text">
												<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
												<span><strong>Edit</strong></span>
											</button>
										</form></li>
									<li><form action="DeleteImage" method="post">
											<button type="submit" value="${image.imageId}"
												name="deleteImage" class="btn btn-default a-btn-slide-text">
												<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
												<span><strong>Delete</strong></span>
											</button>
										</form></li>
								</ul>
							</nav>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td>Total size :${Math.round(totalSize/1024)}KB</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>


</body>
</html>