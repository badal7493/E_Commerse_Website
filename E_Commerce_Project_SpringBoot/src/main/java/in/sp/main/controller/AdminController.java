package in.sp.main.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import in.sp.main.dto.ProductDTO;
import in.sp.main.model.Category;
import in.sp.main.model.Product;
import in.sp.main.service.CategoryService;
import in.sp.main.service.ProductService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;





@Controller
public class AdminController {
	
	
	public static String uploadDir=System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/admin")
	public String adminHome() 
	{
		return "adminHome";
	}
	
	//category Section
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) 
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model)
	{
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category)
	{
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id)
	{
		
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	
    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model) 
    {
    	Optional<Category> category =categoryService.getCategoryById(id);
    	if(category.isPresent())
    	{
    		model.addAttribute("category",category.get());
    		return "categoriesAdd";
    	}
    	else
        return "404";
    }
    
	//Product Section
    
    @GetMapping("/admin/products")
	public String productsGet(Model model)
	{
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}
	
    @GetMapping("/admin/products/add")
   	public String productAddGet(Model model)
   	{
   		model.addAttribute("productDto", new ProductDTO());
   		model.addAttribute("categories", categoryService.getAllCategory());
   		return "productsAdd";
   	}
    
    @PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDto, 
			       @RequestParam("productImage")MultipartFile file,
			       @RequestParam("imgName")String imgName) throws IOException
	            {
    	
    	Product product=new Product();
    	product.setId(productDto.getId());
    	product.setName(productDto.getName());
    	product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
    	product.setPrice(productDto.getPrice());
    	product.setWeight(productDto.getWeight());
    	product.setDescription(productDto.getDescription());
    	String imageUUID;
    	if(!file.isEmpty())
    	{
    		imageUUID=file.getOriginalFilename();
    		Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
    		Files.write(fileNameAndPath, file.getBytes());
    	}
    	else 
    	{
    		imageUUID=imgName;
    	}
    	product.setImageName(imageUUID);
		productService.addProduct(product);
		return "redirect:/admin/products";
	}
   	
    @GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id)
	{
		
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
    
    @GetMapping("/admin/product/update/{id}")
    public String updateProductGet(@PathVariable long id, Model model) {
       
    	Product product=productService.getProductById(id).get();
    	ProductDTO productDto=new ProductDTO();
    	
    	productDto.setId(product.getId());
    	productDto.setName(product.getName());
    	productDto.setCategoryId(product.getCategory().getId());
    	productDto.setPrice(product.getPrice());
    	productDto.setWeight(product.getWeight());
    	productDto.setDescription(product.getDescription());
    	productDto.setImageName(product.getImageName());
    	
    	model.addAttribute("categories", categoryService.getAllCategory());
    	model.addAttribute("productDto", productDto);
        
    	
    	
    	return "productsAdd";
    }
    
    @PostMapping("/submit")
    public ResponseEntity<String> submit() {
        return ResponseEntity.ok("Success");
    }
    

    }

    


