
function loadProducts(categoryId) {
    fetch(`/products/byCategory?categoryId=${categoryId}`)
        .then(response => response.text())
        .then(html => {
            document.getElementById("products-container").innerHTML = html;
        })
        .catch(() => {
            document.getElementById("products-container").innerHTML = "<p>Error loading products.</p>";
        });
}

    function addClickEventListenerOnCategories() {

        const categoryLinks = document.querySelectorAll(".category-link");

        categoryLinks.forEach(link => {
            link.addEventListener("click", function (e) {
                e.preventDefault();
                const categoryId = this.getAttribute("data-id");
                loadProducts(categoryId);
            });
        });

        if (categoryLinks.length > 0) {
            const firstCategoryId = categoryLinks[0].getAttribute("data-id");
            loadProducts(firstCategoryId);
        }
    }

    document.addEventListener('DOMContentLoaded', addClickEventListenerOnCategories);