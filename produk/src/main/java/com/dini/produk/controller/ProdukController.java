/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dini.produk.controller;

import com.dini.produk.entity.Produk;
import com.dini.produk.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @Dell
 */
@RestController
@RequestMapping("api/v1/produk")
public class ProdukController {

    @Autowired
    private ProdukService produkService;
    //memanggil data semuanya
    @GetMapping
    public List<Produk> getAll() {
        return produkService.getAll();
    }
    //memanggil data sesuai id
    @GetMapping(path = "{id}")
    public Produk getProduk(@PathVariable("id") Long id){
        return produkService.getProduk(id);
    }
    //menambahkan data
    @PostMapping
    public void insertProduk(@RequestBody Produk produk) {
        produkService.insert(produk);
    }
    //mengupdate data
    @PutMapping(path = "{id}")
    public void update (@PathVariable("id") Long id,
        @RequestParam(required = false) String kode,
        @RequestParam(required = false) String nama,
        @RequestParam(required = false) String satuan) 
    {
        produkService.update(id, kode, nama, satuan);
    }
    //menghapus data
    @DeleteMapping(path = "{id}")
    public void deleteProduk(@PathVariable("id") Long id) {
        produkService.deleteProduk(id);
    }
}
