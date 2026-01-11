#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

out vec4 fragColor;

void main() {
    vec3 col = texture2D(InSampler, texCoord).rgb;

    vec3 avg_color = (texture2D(InSampler, vec2(0.0, 0.0)).rgb + texture2D(InSampler, vec2(1.0, 0.0)).rgb + texture2D(InSampler, vec2(0.0, 1.0)).rgb + texture2D(InSampler, vec2(1.0, 1.0)).rgb + texture2D(InSampler, vec2(0.5, 0.5)).rgb) / 5.0;

    if (length(col) > length(avg_color)) {
        fragColor = vec4(1.0);
    } else {
        fragColor = vec4(0.0, 0.0, 0.0, 1.0);
    }
}