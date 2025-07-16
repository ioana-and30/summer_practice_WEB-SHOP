
function loadProducts(categoryId, sort='')
{
    let url = `/products/byCategory?categoryId=${categoryId}`;
    if (sort) {
        url += `&sort=${sort}`;
    }
    fetch(url)
        .then(response => response.text())
        .then(html => {
            document.getElementById("products-container").innerHTML = html;
            addClickEventListenerOnProducts()
            addSortChangeListener(categoryId, sort);
        })
        .catch(() => {
            document.getElementById("products-container").innerHTML = "<p>Error loading products.</p>";
        });
}

function addSortChangeListener(categoryId, currentSort) {
    const sortSelect = document.getElementById('sortSelect');
    if (!sortSelect) return;

    sortSelect.value = currentSort;

    sortSelect.onchange = function () {
        const selectedSort = this.value;
        const newUrl = `?category=${categoryId}&sort=${selectedSort}`;
        history.pushState({categoryId: categoryId, sort: selectedSort}, "", newUrl);
        loadProducts(categoryId, selectedSort);
    };
}
function addClickEventListenerOnProducts() {
    const productLinks = document.querySelectorAll(".product-link");
    productLinks.forEach(link => {
        link.addEventListener("click", function (e) {
            e.preventDefault();
            const productId = this.getAttribute("data-product-id");
            loadProduct(productId);
        });
    });
}

function getCategoryFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('category');
}

function getProductIdFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('productId');
}

function getSortFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('sort') || '';
}

function addClickEventListenerOnCategories() {
    const categoryLinks = document.querySelectorAll(".category-link");

    categoryLinks.forEach(link => {
        link.addEventListener("click", function (e) {
            e.preventDefault();
            const categoryId = this.getAttribute("data-id");
            const sort = getSortFromUrl();  // ia sortarea curentă
            loadProducts(categoryId, sort);
            history.pushState({categoryId: categoryId, sort: sort}, "", `?category=${categoryId}&sort=${sort}`);
        });
    });

    const productId = getProductIdFromUrl();
    if (productId) {
        loadProduct(productId, false);
    } else {
        let categoryId = getCategoryFromUrl();
        let sort = getSortFromUrl();

        if (!categoryId && categoryLinks.length > 0) {
            categoryId = categoryLinks[0].getAttribute("data-id");
            history.replaceState({categoryId: categoryId, sort: sort}, "", `?category=${categoryId}&sort=${sort}`);
        }
        loadProducts(categoryId, sort);
    }
}

window.addEventListener('popstate', function(event) {
    console.log("popstate event", event.state);
    if (event.state && event.state.categoryId) {
        loadProducts(event.state.categoryId, event.state.sort || '');
    }
});

document.addEventListener('DOMContentLoaded', addClickEventListenerOnCategories);
