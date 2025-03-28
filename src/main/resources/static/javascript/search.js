document.getElementById("searchButton").addEventListener("click", searchProduct);
document.getElementById("searchInput").addEventListener("keyup", function(event) {
    if (event.key === "Enter") searchProduct();
});

function searchProduct() {
    let input = document.getElementById("searchInput").value.trim();
    let resultsDiv = document.getElementById("searchResults");
    let productList = document.getElementById("productList");
    let paginationDiv = document.getElementById("pagination");

    if (input === "") {
        // Nếu input trống -> Quay về trang home/index
        window.location.href = "/home/index";
        return;
    }

    fetch(`/api/products/search?name=${encodeURIComponent(input)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Lỗi API: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            resultsDiv.innerHTML = "";
            productList.classList.add("d-none");
            resultsDiv.classList.remove("d-none");
            paginationDiv.classList.add("d-none");

            if (data.length > 0) {
                data.forEach(product => {
                    let div = document.createElement("div");
                    div.className = "col-md-4";
                    div.innerHTML = `
                        <div class="card m-2">
                            <img src="${product.image}" class="card-img-top" alt="Product Image">
                            <div class="card-body m-1">
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text">$${product.price}</p>
                                <a href="/cart/add/${product.id}" class="btn btn-success">Add to Cart</a>
                            </div>
                        </div>`;
                    resultsDiv.appendChild(div);
                });
            } else {
                resultsDiv.innerHTML = `<p class="text-center text-danger w-100">Không tìm thấy sản phẩm!</p>`;
            }
        })
        .catch(error => {
            console.error("Lỗi khi fetch dữ liệu:", error);
            resultsDiv.innerHTML = `<p class="text-center text-danger w-100">Lỗi tìm kiếm sản phẩm! Vui lòng thử lại.</p>`;
        });
}
