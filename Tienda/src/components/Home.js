import React, { useEffect, useState } from "react";
import { Container, Spinner } from "reactstrap";
import ProductItem from "components/ProductItem";
import { getData } from "utils/fetchData";
import Filter from "components/Filter";

const Home = () => {

    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);
    const [loading, setLoading] = useState(true);
    const [order, setOrder] = useState("");
    const [keyword, setKeyword] = useState("");
    const [categorySelected, setCategorySelected] = useState("");
    const [rangeOfPrice, setRangeOfPrice] = useState({ minPrice: 0, maxPrice: 1000000 });

    useEffect(() => {

        getData("productos")
            .then(res => {
                setProducts(res);
                setLoading(false);
            })
            .catch(err => console.log(err));

        getData("categorias")
            .then(res => {
                setCategories(res);
            })
            .catch(err => console.log(err));

    }, []);

    const handleChangeSearch = e => {

        setKeyword(e.target.value);

        let query = e.target.value ? `productos/search?keyword=${e.target.value}` : "productos";

        getData(query)
            .then(res => {
                setProducts(res);
            })
            .catch(err => console.log(err));

    }

    const handleChangeCategory = e => {

        setCategorySelected(e.target.value);

        let query = e.target.value ? `productos/getByCategoria?idCategoria=${e.target.value}` : "productos";
        
        getData(query)
            .then(res => {
                setProducts(res);
            })
            .catch(err => console.log(err));

    }



    const handleChangeRangeOfPrice = e => {
        
        const { name, value } = e.target;
        setRangeOfPrice({ ...rangeOfPrice, [name]: value });

    }

    
    const handleSelectChangeRangeOfPrice = e => {

        const { name, value } = e.target;
        setRangeOfPrice({ ...rangeOfPrice, [name]: value });

        let query = `productos/getByRangeOfPrice?min=${rangeOfPrice.minPrice}&max=${rangeOfPrice.maxPrice}`;
  
        getData(query)
            .then(res => {
                setProducts(res);
            })
            .catch(err => console.log(err));

    }


    if (order === "Precio ascendente") {
        products.sort((a, b) => parseFloat(a.precio) - parseFloat(b.precio));
    } else if (order === "Precio descendente") {
        products.sort((a, b) => parseFloat(b.precio) - parseFloat(a.precio));
    }

    if (loading) return (
        <div className="page-header clear-filter">
            <Container>
                <Spinner color="info" />
            </Container>
        </div>
    )

    return (
        <div className="row">
            <Filter
                setProducts={setProducts}
                products={products}
                categories={categories}
                order={order}
                setOrder={setOrder}
                handleChangeSearch={handleChangeSearch}
                keyword={keyword}
                categorySelected={categorySelected}
                handleChangeCategory={handleChangeCategory}
                rangeOfPrice={rangeOfPrice}
                handleChangeRangeOfPrice={handleChangeRangeOfPrice}
                handleSelectChangeRangeOfPrice={handleSelectChangeRangeOfPrice}
            />
            <div className="col-md-9">
                <div className="row justify-content-center">
                    {
                        products.length === 0 && (
                            <div className="card">
                                <div className="card-body">
                                    No hay productos que coincidan con la busqueda.
                                </div>
                            </div>
                        )
                    }
                    {   products.length > 0 &&
                        products.map(product =>
                            <div className="col-md-4" key={product.idProducto}>
                                <ProductItem product={product} />
                            </div>
                        )
                    }
                </div>
            </div>
        </div>
    )
}

export default Home;