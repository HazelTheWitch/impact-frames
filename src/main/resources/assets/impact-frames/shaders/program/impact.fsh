#version 120
uniform sampler2D DiffuseSampler;

varying vec2 texCoord;

void main(void)
{
    vec3 col = texture2D(DiffuseSampler, texCoord).rgb;

    vec3 avg_color = (texture2D(DiffuseSampler, vec2(0.0, 0.0)).rgb + texture2D(DiffuseSampler, vec2(1.0, 0.0)).rgb + texture2D(DiffuseSampler, vec2(0.0, 1.0)).rgb + texture2D(DiffuseSampler, vec2(1.0, 1.0)).rgb + texture2D(DiffuseSampler, vec2(0.5, 0.5)).rgb) / 5.0;

    if (length(col) > length(avg_color)) {
        gl_FragColor = vec4(1.0);
    } else {
        gl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);
    }
}