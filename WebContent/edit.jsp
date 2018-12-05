<%@include file="index.jsp"%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache");
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("LoginPage.jsp");
	}
%>
<div class="container">
	<div class="jumbotron">
		<h3>Edit the image</h3>
		<form action="UpdateImage" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>Image Name:</label> <input type="text"
					value="${selectedImage.imageName}" name="newImageName"
					autocomplete='off' class="form-control">
			</div>

			<div class="form-group">
				<label>Upload Image</label> <input type="file" name="newImageSrc"
					value ="${selectedImage.imagePath}"
					accept="image/jpg, image/jpeg, image/png" class="form-control" />
			</div>
			<div class="form-group">
				<button type="submit" value="${selectedImage.imageId}"
					name="updateImage" class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-default">Clear</button>
			</div>
		</form>
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
</div>
</body>
</html>