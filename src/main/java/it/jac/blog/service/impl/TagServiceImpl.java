package it.jac.blog.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.blog.model.Tag;
import it.jac.blog.repository.TagRepository;
import it.jac.blog.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagRepository tagRepository;

	@Override
	public Optional<Tag> get(Long id) {
		return tagRepository.findById(id);
	}

	@Override
	public Tag create(Tag c) {
		return tagRepository.save(c);
	}

	@Override
	public void createAll(List<Tag> tags) {
		tagRepository.saveAll(tags);
	}

	@Override
	public void delete(Long id) {
		tagRepository.deleteById(id);
	}

	@Override
	public Tag update(Tag tag, Long id) {
		return tagRepository.findById(id).map(c -> { // update if entity already exists
			tag.setId(c.getId());
			return create(tag);
		}).orElseGet(() -> { // create if entity not exists
			return tagRepository.save(tag);
		});
	}

	@Override
	public List<Tag> alreadyExists(List<Tag> tagsDuplicated) {
		Tag t;
		List<Tag> tags = new ArrayList<>();
		List<String> codes = new ArrayList<>();
		LinkedHashSet<String> noDuplicates;

		for (Tag tag : tagsDuplicated)
			codes.add(tag.getCode());

		noDuplicates = new LinkedHashSet<>(codes);

		for (String code : noDuplicates) {
			t = tagRepository.findFirst1ByCode(code);
			if (t != null && !tags.contains(t)) {
				tags.add(t);
			} else {
				Tag a = new Tag();
				a.setCode(code);
				tags.add(a);
			}
		}

		return tags;
	}
}
