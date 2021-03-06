package com.ecommerce.ecommerce.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.entities.Producto;
import com.ecommerce.ecommerce.repositories.IProductoRepository;
import com.ecommerce.ecommerce.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService{
	
    @Autowired
    @Qualifier("productoRepository")
    private IProductoRepository productoRepository;

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> getAllProductosVisibles() {
		// TODO Auto-generated method stub
		return productoRepository.getAllProductosVisibles();
	}

	@Override
	public Producto getProductoById(long id) {
		// TODO Auto-generated method stub
		return productoRepository.findByIdProducto(id);
	}
	
	@Override
	public List<Producto> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		return productoRepository.searchProduct(keyword);
	}
	
    @Override
    public List<Producto> findByCategoria(String idCategoria) {
        return productoRepository.findByCategoria(idCategoria);
    }
    
    @Override
    public List<Producto> findByVendedor(String idVendedor) {
        return productoRepository.findByVendedor(idVendedor);
    }
    
    @Override
    public List<Producto> findByRangeOfPrice(double min, double max) {
        return productoRepository.findByRangeOfPrice(min, max);
    }

	@Override
	public void deleteProducto(int idProducto) {
		productoRepository.delete(productoRepository.findByIdProducto(idProducto));
	}
	
    @Override
    public Producto traerProductoPorId(long idProducto) {
    	return productoRepository.traerProductoPorId(idProducto);
    }


}
