package com.example.mouna.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mouna.exception.ResourceNotFoundException;
import com.example.mouna.model.Produit;
import com.example.mouna.model.Stock;
import com.example.mouna.repository.ProduitRepository;
import com.example.mouna.repository.StockRepository;

@RestController
@RequestMapping("/projet")
public class StockController {
	@Autowired
    ProduitRepository produitRepository;
	
	@Autowired
    StockRepository stockRepository;
	
	
	
	 @GetMapping("/produits/{id}/stocks")
	    public Page<Stock> getAllStocksByProduitId(@PathVariable (value = "id") Long produitId,
	                                                Pageable pageable) {
	        return stockRepository.findByProduitId(produitId, pageable);
	    }
	    @PostMapping("/produits/{id}/stocks")
	    public Stock createStock(@PathVariable (value = "id") Long produitId,
	                                 @Valid @RequestBody Stock stock) {
	        return produitRepository.findById(produitId).map(produit -> {
	            stock.setProduit(produit);
	            return stockRepository.save(stock);
	        }).orElseThrow(() -> new ResourceNotFoundException("CategorieId " + produitId + " not found"));
	    }
	    @PutMapping("/produits/{id}/stocks/{stockId}")
	    public Stock updateProduit(@PathVariable (value = "id") Long produitId,
	                                 @PathVariable (value = "stockId") Long stockId,
	                                 @Valid @RequestBody Stock stockRequest) {
	        if(!produitRepository.existsById(produitId)) {
	            throw new ResourceNotFoundException("PostId " + produitId + " not found");
	        }

	        return stockRepository.findById(stockId).map(stock -> {
	        	 stock.setAction(stockRequest.getAction());
	        	 stock.setQuantite(stockRequest.getQuantite());
	        	 stock.setCreatedAt(stockRequest.getCreatedAt());
	        	 
	            return stockRepository.save(stock);
	        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + stockId + "not found"));
	    }
	    
	    @DeleteMapping("/produits/{id}/stocks/{stockId}")
	    public ResponseEntity<?> deleteStock(@PathVariable (value = "id") Long produitId,
	                              @PathVariable (value = "stockId") Long stockId) {
	        return stockRepository.findByIdAndProduitId(stockId, produitId).map(stock -> {
	        	stockRepository.delete(stock);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + stockId + " and produitId " + produitId));
	    }


}
