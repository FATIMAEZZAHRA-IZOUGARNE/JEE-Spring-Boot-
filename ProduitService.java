package com.fstm.jpa.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fstm.jpa.entities.Produit;
import com.fstm.jpa.repositories.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduitById(Integer id) {
        return produitRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produit not found with id: " + id));
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Integer id, Produit newProduit) {
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produit not found with id: " + id));
        produit.setNom(newProduit.getNom());
        produit.setPrix(newProduit.getPrix());
        return produitRepository.save(produit);
    }

    public void deleteProduit(Integer id) {
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produit not found with id: " + id));
        produitRepository.delete(produit);
    }
}
