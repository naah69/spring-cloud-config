/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cloud.config.server.environment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dylan Roberts
 */
public class VaultEnvironmentRepositoryFactory implements EnvironmentRepositoryFactory<VaultEnvironmentRepository,
		VaultEnvironmentProperties> {
	private ObjectProvider<HttpServletRequest> request;
	private EnvironmentWatch watch;

	public VaultEnvironmentRepositoryFactory(ObjectProvider<HttpServletRequest> request, EnvironmentWatch watch) {
		this.request = request;
		this.watch = watch;
	}

	@Override
	public VaultEnvironmentRepository build(VaultEnvironmentProperties environmentProperties) {
		VaultEnvironmentRepository repository = new VaultEnvironmentRepository(request, watch, new RestTemplate(),
				environmentProperties);
		return repository;
	}
}
