package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.esgi.dao.CategoryRepository;
import fr.esgi.domain.Category;
import fr.esgi.service.dto.CategoryDTO;
import fr.esgi.service.impl.CategoryServiceImpl;
import fr.esgi.service.mapper.CategoryMapper;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

	private static final long ID = 1L;

	private static final String WORDING = "Informatiques";

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private CategoryMapper categoryMapper;

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	private static Category getCategory() {
		Category category = new Category();
		category.setId(ID);
		category.setWording(WORDING);
		return category;
	}
	
	private static CategoryDTO getCategoryDTO() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(ID);
		categoryDTO.setWording(WORDING);
		return categoryDTO;
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsOK() {
		// Given
		List<Category> categorys = new ArrayList<>();
		categorys.add(getCategory());

		// When
		when(categoryRepository.findCategoriesByWording(anyString())).thenReturn(categorys);
		when(categoryMapper.categoryToCategoryDTO(((Category) any()))).thenReturn(getCategoryDTO());

		// Then
		assertThat(categoryServiceImpl.findAllByWording(anyString())).isNotNull();
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsEmpty() {
		// Given
		List<Category> categorys = new ArrayList<>();

		// When
		when(categoryRepository.findCategoriesByWording(anyString())).thenReturn(categorys);

		// Then
		assertThat(categoryServiceImpl.findAllByWording(anyString())).isEmpty();
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsKO() {
		// Given
		List<Category> categorys = null;

		// When
		when(categoryRepository.findCategoriesByWording(anyString())).thenReturn(categorys);

		// Then
		assertThatThrownBy(() -> categoryServiceImpl.findAllByWording(anyString()))
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldFindAllCategoriesWhenIsOK() {
		// Given
		List<Category> categorys = new ArrayList<>();
		categorys.add(getCategory());

		// When
		when(categoryRepository.findAll()).thenReturn(categorys);
		when(categoryMapper.categoryToCategoryDTO(((Category) any()))).thenReturn(getCategoryDTO());

		// Then
		assertThat(categoryServiceImpl.findAll()).isNotEmpty();
	}

	@Test
	public void shouldFindAllCategoriesWhenIsEmpty() {
		// Given
		List<Category> categorys = new ArrayList<>();

		// When
		when(categoryRepository.findAll()).thenReturn(categorys);

		// Then
		assertThat(categoryServiceImpl.findAll()).isEmpty();
	}

	@Test
	public void shouldFindAllWhenIsKO() {
		// Given
		List<Category> categorys = null;

		// When
		when(categoryRepository.findAll()).thenReturn(categorys);

		// Then
		assertThatThrownBy(() -> categoryServiceImpl.findAll())
		.isInstanceOf(NullPointerException.class);
	}
}
