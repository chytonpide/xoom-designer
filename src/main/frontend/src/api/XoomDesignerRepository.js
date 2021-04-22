import Repository from './Repository'

const resources = Object.freeze({
	generation: '/generation-settings',
	settingsFile: '/generation-settings/file'
})

function ensure(response, status) {
	if(response.status === 500) {
		return response.json().then(json => Promise.reject(json));
	}
	if (response.status !== status) {
		throw Error(`HTTP ${response.status}: ${response.statusText}.`);
	}
	return response;
}


function ensureOk(response) {
	return ensure(response, 200);
}

async function repoPost(path, body) {
	return Repository.post(path, body)
	.then(ensureOk)
	.then(res => res.json());
}

async function repoPatch(path, body) {
	return Repository.patch(path, body)
	.then(ensureOk)
	.then(res => res.json());
}

export default {
	postGenerationSettings(context, model, deployment, projectDirectory, useAnnotations, useAutoDispatch) {
		return repoPost(resources.generation, {
			context, model, deployment, projectDirectory, useAnnotations, useAutoDispatch
		});
	},

	downloadSettingsFile(context, model, deployment, projectDirectory, useAnnotations, useAutoDispatch) {
		return repoPost(resources.settingsFile, {
			context, model, deployment, projectDirectory, useAnnotations, useAutoDispatch
		});
	},

	uploadSettingsFile(encodedFile) {
		return repoPatch(resources.settingsFile, {
			"encoded": encodedFile
		});
	},
}
