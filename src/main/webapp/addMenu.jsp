<!-- AddMenu.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Menu</title>
    <link rel="stylesheet" type="text/css" href="addMenu.css">
</head>
<body>
    <h2>Add Menu</h2>
    <form action="AddMenuServlet" method="post">
        <!-- Input fields for Menu attributes -->
        <label for="restaurantId">Restaurant ID:</label>
        <input type="text" id="restaurantId" name="restaurantId" required><br>

        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" name="itemName" required><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" required></textarea><br>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" required><br>

        <label for="rating">Rating:</label>
        <input type="number" id="rating" name="rating" step="0.1" required><br>

        <label for="isAvailable">Is Available:</label>
        <select id="isAvailable" name="isAvailable" required>
            <option value="Available">Available</option>
            <option value="Not Available">Not Available</option>
        </select><br>

        <label for="imagepath">Image Path:</label>
        <input type="text" id="imagepath" name="imagepath" required><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
